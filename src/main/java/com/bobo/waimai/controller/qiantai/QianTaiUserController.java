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
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.pojo.User;
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
}
