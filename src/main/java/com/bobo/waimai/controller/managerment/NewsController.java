package com.bobo.waimai.controller.managerment;

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
import com.bobo.waimai.commons.BaseUrl;
import com.bobo.waimai.commons.DataGridResult;
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.FileUtils;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.commons.utils.UUIDUtils;
import com.bobo.waimai.pojo.News;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.service.NewsService;
import com.bobo.waimai.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo on 2018/2/3/8:54.
 */
@Controller
@RequestMapping(value = "news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsTypeService newsTypeService;

    @RequestMapping(value = "/index.action")
    public ModelAndView getIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("news/index");
        return modelAndView;
    }

    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String NewsList(Integer limit,Integer offset){
        Long total=newsService.countAll();
        List<News> rows=new ArrayList();
        rows=newsService.getPageNews(limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }
    @RequestMapping(value = {"add.action","edit.action"})
    public ModelAndView addOrUpdateView(Integer newsId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("news/addOrUpdate");
        Map<String,Object> map=new HashMap<>();
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes=newsTypeService.getAllNewsTypes();
        News news=null;
        if (newsId!=null){
            news= newsService.getNewsById(newsId);
        }
        map.put("news",news);
        map.put("newsTypes",newsTypes);
        map.put("newsId",newsId);
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping(value = "getNews.action")
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

    @RequestMapping(value = "/savePicture.action")
    @ResponseBody
    public String  savePicture(MultipartFile uploadFile, HttpServletRequest request){
        BaseUrl baseUrl=null;
        if (uploadFile.isEmpty()==false){
            String picName= UUIDUtils.getUUID();
            String[] strings = uploadFile.getOriginalFilename().split("\\.");
            String houzhui=strings[1];
            String bashPath=request.getSession().getServletContext().getRealPath("/")+"waimai"+File.separator+
                    "news"+File.separator;
            File distincation= FileUtils.createFile(bashPath,picName,houzhui);
            if (distincation.exists()==false){
                try {
                    distincation.createNewFile();
                    System.err.println(distincation.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                uploadFile.transferTo(distincation);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+"waimai/news/"+picName+"."+houzhui;

            baseUrl=new BaseUrl(GlobalFianlVar.ERROR,url);
            return JsonUtils.objectToJson(baseUrl);
        }
        baseUrl=new BaseUrl(GlobalFianlVar.SUCCESS,null);
        return JsonUtils.objectToJson(baseUrl);
    }

    @RequestMapping(value = "/saveNews.action")
    public String saveNews(News news){
        /**
         * 判断news中是否含有ID，如果没有ID，则证明是新添加的数据，否则代表着是修改的数据
         */
        if (news.getNewsId()!=null){
            newsService.updateNews(news);

        }else {
            newsService.addNews(news);

        }
        return "redirect:/news/index.action";
    }

    @RequestMapping(value = "/details.action")
    public ModelAndView getNewsDetatils(Integer newsId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("news/newsDetail");
        News news = newsService.getNewsById(newsId);
        Map<String,Object> map=new HashMap<>();
        map.put("news",news);
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping(value = "deleteNews.action")
    @ResponseBody
    public String deleteNews(@RequestParam(value = "newsId",required = true)Integer newsId){
        BaseJson baseJson=null;
        try {
            newsService.deleteNewsById(newsId);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");

            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }

    }

    @RequestMapping(value = "/deleteMore.action")
    @ResponseBody
    public String deleteNewsMore(@RequestBody Integer[] newsIds){

        BaseJson baseJson=null;
        if (newsIds.length>0){
            for (int i=0;i<newsIds.length;i++){
                try {
                    newsService.deleteNewsById(newsIds[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        }else {
            return null;
        }

    }

}
