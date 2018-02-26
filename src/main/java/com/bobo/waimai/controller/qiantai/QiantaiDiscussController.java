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
import com.bobo.waimai.commons.DiscussResult;
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.pojo.*;
import com.bobo.waimai.service.ConsumeService;
import com.bobo.waimai.service.DiscussService;
import com.bobo.waimai.service.FoodTypeService;
import com.bobo.waimai.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/24/10:45.
 */
@Controller
@RequestMapping(value = "qiantaiDiscuss")
public class QiantaiDiscussController {

    @Autowired
    private DiscussService discussService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private NewsTypeService newsTypeService;
    @Autowired
    private ConsumeService consumeService;
    @RequestMapping(value = "index.action")
    public ModelAndView Index(HttpServletRequest request, HttpServletResponse response,Integer consumeId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/discuss");
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        Cookie[] cookies = request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("cousumeId")){
                cookies[i].setValue(String.valueOf(consumeId));
            }else{
                Cookie cookie=new Cookie("cunsumeId",String.valueOf(consumeId));
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
       Consume consume= consumeService.getConsumeById(consumeId);
        modelAndView.addObject("consume",consume);
        return modelAndView;
    }

    @RequestMapping(value = "add.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addDiscuss(@RequestBody DiscussResult discussResult,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        BaseJson baseJson=null;
        try {
            discussService.addDiscuss(discussResult,user);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,"评论成功");
            return JsonUtils.objectToJson(baseJson);
        }catch (Exception e){
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误，评论失败");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addresses(Integer limit,Integer offset,HttpServletRequest request){

        User user= (User) request.getSession().getAttribute("user");
        Long total=discussService.countAll(user.getUserId());
        List<Discuss> rows=new ArrayList();
        rows=discussService.discusses(limit,offset,user.getUserId());
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }
}
