package com.bobo.waimai.controller.qiantai;

//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//  


import com.bobo.waimai.commons.BaseJson;
import com.bobo.waimai.commons.DataGridResult;
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.pojo.News;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.service.FoodTypeService;
import com.bobo.waimai.service.NewsService;
import com.bobo.waimai.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/22/9:34.
 */
@Controller
@RequestMapping(value = "qiantaiNews")
public class QiantaiNewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsTypeService newsTypeService;
    @Autowired
    private FoodTypeService foodTypeService;
    @RequestMapping(value = "index.action")
    public ModelAndView index(HttpServletRequest request,Integer newsTypeId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/news");
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        modelAndView.addObject("newsTypeId",newsTypeId);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        return modelAndView;
    }


    @RequestMapping(value = "detailsPage.action")
    public ModelAndView detailsPage(HttpServletRequest request,Integer newsId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/newsDetails");
        modelAndView.addObject("newsId",newsId);
        return modelAndView;
    }

    @RequestMapping(value = "getNews.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit(Integer newsId){
        BaseJson baseJson=null;
        News news=null;
        if (newsId!=null){
            news= newsService.getNewsById(newsId);
        }
        baseJson=new BaseJson(GlobalFianlVar.SUCCESS,news);
        return JsonUtils.objectToJson(baseJson);
    }


    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String NewsList(Integer limit,Integer offset,@RequestParam(defaultValue = "1") Integer newsTypeId){
        Long total=newsService.countAll();
        List<News> rows=new ArrayList();
        rows=newsService.getNews(limit,offset,newsTypeId);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }
}
