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


import com.bobo.waimai.commons.DataGridResult;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.pojo.Consume;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/23/20:32.
 */
@Controller
@RequestMapping(value = "qiantaiConsume")
public class QiantaiConsumeController {
    @Autowired
    private ConsumeService consumeService;

    @RequestMapping(value = "/listConsumes.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addresses(Integer limit,Integer offset,HttpServletRequest request){

        User user= (User) request.getSession().getAttribute("user");
        Long total=consumeService.countAll(user.getUserId());
        List<Consume> rows=new ArrayList();
        rows=consumeService.getAddresses(limit,offset,user.getUserId());
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }
}
