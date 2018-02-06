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
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.commons.utils.StringUtils;
import com.bobo.waimai.pojo.Manager;
import com.bobo.waimai.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by bobo on 2018/1/30/19:01.
 */
@Controller
@RequestMapping(value = "admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login.action")
    @ResponseBody
    public String login(@RequestBody Manager manager, HttpServletRequest request){
        BaseJson baseJson=null;
        if (StringUtils.isEmpty(manager.getManagerName())==false&&StringUtils.isEmpty(manager.getManagerPassword())==false){
            int count=managerService.getCountByNameAndPassword(manager.getManagerName(), manager.getManagerPassword());
            if (count==1){
                baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
//                将管理员的信息添加到session中去
                HttpSession session = request.getSession();
//                在session中的键为admin
                session.setAttribute("admin",manager);
                return JsonUtils.objectToJson(baseJson);
            }else {
                baseJson=new BaseJson(GlobalFianlVar.ERROR,"用户名或者是密码错误，请重新输入");
                return JsonUtils.objectToJson(baseJson);
            }
        }
         baseJson=new BaseJson(GlobalFianlVar.ERROR,"用户名或者密码没有输入");
        return JsonUtils.objectToJson(baseJson);
    }

    /**
     * 修改管理员的密码,跳转页面
     * @return
     */
    @RequestMapping(value = "updatePasswordPage.action")
    public ModelAndView updatePasswordPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/updatePassword");
        return modelAndView;
    }

    @RequestMapping(value = "updatePassword.action",produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String updatePassword(@RequestParam(name = "newPassword",required = true)String newPassword,
                                       HttpSession session){
        BaseJson baseJson=null;
        Manager manager= (Manager) session.getAttribute("admin");
        if (StringUtils.isEmpty(newPassword)==false){
            try {
                managerService.updatePasswordByName(manager.getManagerName(),newPassword);
                session.removeAttribute("admin");
                baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
                return JsonUtils.objectToJson(baseJson);
            } catch (Exception e) {
                baseJson=BaseJson.setError(GlobalFianlVar.ERROR,"修改密码失败");
                return JsonUtils.objectToJson(baseJson);
            }
        }else {
            baseJson=BaseJson.setError(GlobalFianlVar.ERROR,"新密码为空");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    /**
     * 从系统中退出
     * @param session
     * @return
     */
    @RequestMapping(value = "layout.action")
    @ResponseBody
    public String layout(HttpSession session){
//        销毁session中的数据
        session.removeAttribute("admin");
        BaseJson baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
        return JsonUtils.objectToJson(baseJson);
    }
}
