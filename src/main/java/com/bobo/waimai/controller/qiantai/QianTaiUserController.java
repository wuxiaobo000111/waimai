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
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.service.FoodTypeService;
import com.bobo.waimai.service.NewsTypeService;
import com.bobo.waimai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/21/15:45.
 */
@Controller
@RequestMapping(value = "qiantaiuser")
public class QianTaiUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NewsTypeService newsTypeService;
    @Autowired
    private FoodTypeService foodTypeService;
    @RequestMapping(value = "validateUserName.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String validateUserName(String userName){
        User user = userService.validateUserName(userName);
        BaseJson baseJson=null;
        if (user!=null){
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        }else{
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"这个名字已经重复");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "loginPage.action")
    public ModelAndView loginPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/login");
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        return modelAndView;
    }


    @RequestMapping(value = "login.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody User user, HttpServletRequest request){
        BaseJson baseJson=null;
        if (user!=null){
          Integer count=userService.validateUser(user);
          if (count==1){
              User currentuser=userService.getUserByUserName(user.getUserName());
              request.getSession().setAttribute("user",currentuser);
              baseJson=new BaseJson(GlobalFianlVar.SUCCESS,currentuser);
              return JsonUtils.objectToJson(baseJson);
          }else{
              baseJson=new BaseJson(GlobalFianlVar.ERROR,"该用户不存在，请注册新的用户");
              return JsonUtils.objectToJson(baseJson);
          }
        }else{
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"你输入的用户名和密码为空，请重新输入");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "lagout.action")
    public String logout(HttpServletRequest request){
        if (request.getSession().getAttribute("user")!=null){
            request.getSession().removeAttribute("user");
            return "redirect:/index.action";
        }
        return null;
    }

    @RequestMapping(value = "center.action")
    public ModelAndView center(){
        ModelAndView modelAndView=new ModelAndView();
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        modelAndView.setViewName("qiantai/center");
        return modelAndView;
    }

    @RequestMapping(value = "personal.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String personal(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        BaseJson baseJson=null;
        if (user!=null){
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,user);
            return JsonUtils.objectToJson(baseJson);
        }else{
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"这个用户不存在，傻了吧");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "updatePage.action")
    public ModelAndView updatePage(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        modelAndView.setViewName("qiantai/update");
        User user= (User) request.getSession().getAttribute("user");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "update.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody User user,HttpServletRequest request){
        BaseJson baseJson=null;
        try {
            userService.updateUser(user);
           request.getSession().removeAttribute("user");
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"更新失败，傻了吧");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }

}
