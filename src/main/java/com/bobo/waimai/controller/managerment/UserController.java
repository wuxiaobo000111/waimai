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
import com.bobo.waimai.commons.utils.StringUtils;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by bobo on 2018/1/31/13:38.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "index.action")
    public ModelAndView toIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user/index");
        return modelAndView;
    }
    @RequestMapping(value = "list.action")
    @ResponseBody
    public String userList(Integer limit,Integer offset) {
        Long total=userService.countAll();
        List<User> rows=new ArrayList();
        rows=userService.getPageUser(limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = {"add.action","edit.action"})
    public ModelAndView addOrEdit(Integer userId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user/addOrUpdate");
        User user=null;
        if (userId!=null){
          user= userService.getUserById(userId);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("user",user);
        modelAndView.addAllObjects(result);
        return modelAndView;
    }

    @RequestMapping(value = "addUser.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addUser(@RequestBody User user){
        BaseJson baseJson=null;
        user.setUserRegisterTime(new Date());
        try {
            userService.addUser(user);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            e.printStackTrace();
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "updateUser.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updadteUser(@RequestBody User user){
        BaseJson baseJson=null;
        try {
            userService.updateUser(user);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            e.printStackTrace();
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            return JsonUtils.objectToJson(baseJson);
        }
    }
//验证用户名是否存在，如果存在返回
    @RequestMapping(value = "validateUser.action")
    @ResponseBody
    public String validateUserByUserName(String userName){
        BaseJson baseJson=null;
        if (StringUtils.isEmpty(userName)){
            User user = userService.validateUserName(userName);
            if (user!=null){
                baseJson =new BaseJson(GlobalFianlVar.SUCCESS,"用户名已经存在");
                return JsonUtils.objectToJson(baseJson);
            }else {
                return null;
            }
        }
        return null;
    }


    @RequestMapping(value = "deleteUser.action")
    @ResponseBody
    public String deleteUser(@RequestParam(name = "userId",required = true)Integer userId){
        int row=userService.deleteUserById(userId);
        BaseJson baseJson=null;
        if (row==1){
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        }else {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"系统内部发生错误,删除失败");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "deleteMore.action")
    @ResponseBody
    public String deleteUsers(@RequestBody Integer[] ids){
        BaseJson baseJson=null;
        if (ids.length>0){
            for (int i=0;i<ids.length;i++){
                userService.deleteUserById(ids[i]);
            }
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        }else {
            return null;
        }
    }

}
