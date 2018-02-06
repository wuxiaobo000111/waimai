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
import com.bobo.waimai.commons.DataGridResult;
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo on 2018/2/2/17:30.
 */
@Controller
@RequestMapping(value = "newsType")
public class NewsTypeController {
    @Autowired
    private NewsTypeService newsTypeService;


    @RequestMapping(value = "/index.action")
    public ModelAndView getIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("newsType/index");
        return modelAndView;
    }

    @RequestMapping(value = "list.action",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String foodTypeList(Integer limit,Integer offset) {
        Long total=newsTypeService.countAll();
        List<NewsType> rows=new ArrayList();
        rows=newsTypeService.getPageNewsType(limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = {"edit.action","add.action"})
    public ModelAndView addOrEdit(Integer newsTypeId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("newsType/addOrUpdate");
        NewsType newsType=null;
        if (newsTypeId!=null){
            newsType= newsTypeService.getNewsTypeById(newsTypeId);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("newsType",newsType);
        modelAndView.addAllObjects(result);
        return modelAndView;
    }

    @RequestMapping(value = "addNewsType.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addNewsType(@RequestBody NewsType newsType){
        BaseJson baseJson=null;
        try {
            newsTypeService.addNewsType(newsType);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            e.printStackTrace();
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "updateNewsType.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updadteNewsType(@RequestBody NewsType newsType){
        BaseJson baseJson=null;
        try {
            newsTypeService.updateNeById(newsType);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            e.printStackTrace();
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            return JsonUtils.objectToJson(baseJson);
        }
    }


}
