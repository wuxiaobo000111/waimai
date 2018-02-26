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
import com.bobo.waimai.pojo.Food;
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.pojo.extend.FoodDiscuss;
import com.bobo.waimai.service.FoodService;
import com.bobo.waimai.service.FoodTypeService;
import com.bobo.waimai.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/22/16:27.
 */
@Controller
@RequestMapping(value = "qiantaiFood")
public class QianTaiFoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private NewsTypeService newsTypeService;
    @Autowired
    private FoodTypeService foodTypeService;

    @RequestMapping(value = "index.action")
    public ModelAndView index(HttpServletRequest request,Integer foodTypeId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/foods");
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        modelAndView.addObject("foodTypeId", foodTypeId);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        return modelAndView;
    }

    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String NewsList(Integer limit,Integer offset,@RequestParam(defaultValue = "1") Integer foodTypeId){
        Long total=foodService.countAll();
        List<Food> rows=new ArrayList();
        rows=foodService.getFoods(limit,offset,foodTypeId);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = "detailsPage.action")
    public ModelAndView detailsPage(HttpServletRequest request, HttpServletResponse response,Integer foodId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/newsDetails");
        modelAndView.addObject("foodId",foodId);
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        return modelAndView;
    }

    @RequestMapping(value = "/listDiscuss.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listDiscuss(HttpServletRequest request){
        BaseJson baseJson=null;
        Cookie[] cookies = request.getCookies();
        Integer foodId=null;
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("foodId")){
                foodId=Integer.parseInt(cookies[i].getValue());
                break;
            }
        }
        List<FoodDiscuss> foodDiscusses=new ArrayList<>();
        foodDiscusses=foodService.getDisucssByFoodId(foodId);
        baseJson=new BaseJson(GlobalFianlVar.SUCCESS,foodDiscusses);
        return JsonUtils.objectToJson(baseJson);
    }
}
