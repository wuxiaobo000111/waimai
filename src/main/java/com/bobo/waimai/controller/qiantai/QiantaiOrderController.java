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
import com.bobo.waimai.commons.Ids;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.commons.utils.StringUtils;
import com.bobo.waimai.pojo.Consume;
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.pojo.extend.ExtendAddress;
import com.bobo.waimai.pojo.extend.OrderItem;
import com.bobo.waimai.service.*;
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
import java.util.Date;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/23/9:54.
 */
@Controller
@RequestMapping(value = "qiantaiOrder")
public class QiantaiOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private NewsTypeService newsTypeService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private ConsumeService consumeService;
    /**
     * 逻辑：
    则跳转到订单页面中去，同时把所有的数据写入到订单表中，
     * 删除购物车中的记录。
     * 在订单表中只保存最近的一次的订单详请，查询的时候，根据个人的信息进行查询
     */

    @RequestMapping(value = "account.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String account(HttpServletRequest request, HttpServletResponse response, @RequestBody Ids ids){
       BaseJson baseJson=null;
       String str="";
       for (int i=0;i<ids.getIds().length;i++){
           str+=ids.getIds()[i].toString()+"-";
       }
       str=str+ids.getAddressId();
       Cookie cookie=new Cookie("order",str);
       cookie.setPath("/");
       response.addCookie(cookie);
       baseJson=new BaseJson(GlobalFianlVar.SUCCESS,"");
       return JsonUtils.objectToJson(baseJson);
    }

    @RequestMapping(value = "order.action")
    public ModelAndView order(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/order");
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
//       获取cookie中的order的值

        return modelAndView;
    }

    @RequestMapping(value = "pay.action")
    public ModelAndView pay(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        modelAndView.setViewName("qiantai/pay");

        String value="";
        Cookie[] cookies = request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("order")){
                value= cookies[i].getValue();
                Cookie cookie=new Cookie("order","");
                cookie.setPath("");
                response.addCookie(cookie);
                break;
            }
        }
        if (StringUtils.isEmpty(value)==false){
            String[] strings = value.split("-");
            Integer[] ids=new Integer[strings.length-1];
            Integer addressId=Integer.parseInt(strings[strings.length-1]);
            for (int i=0;i<ids.length;i++){
                ids[i]=Integer.parseInt(strings[i]);
            }
            User user= (User) request.getSession().getAttribute("user");
            orderService.addOrder(ids,addressId,user);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String NewsList(Integer limit,Integer offset,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        Long total=orderService.countAll(user.getUserId());
        List<OrderItem> orderItems=new ArrayList();
        orderItems=orderService.getOrders(limit,offset,user.getUserId());
        DataGridResult result=new DataGridResult(orderItems,total);
        return JsonUtils.objectToJson(result);

    }

    @RequestMapping(value = "/getOther.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getOther(HttpServletRequest request){
        BaseJson baseJson=null;
        User user= (User) request.getSession().getAttribute("user");
        ExtendAddress extendAddress=orderService.getExtendAddress(user.getUserId());
        if(extendAddress!=null){
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,extendAddress);
        }
        return JsonUtils.objectToJson(baseJson);
    }


    @RequestMapping(value = "/zhifu.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String zhifu(HttpServletRequest request,Integer totalMoney){
        BaseJson baseJson=null;
        User user= (User) request.getSession().getAttribute("user");
        List<OrderItem> orderItems = orderService.getOrders(10000, 0, user.getUserId());
        String consumeFood="";
        for (int i=0;i<orderItems.size();i++){
            if (i!=orderItems.size()-1){
                consumeFood+=orderItems.get(i).getFoodName()+"-";
            }else{
                consumeFood+=orderItems.get(i).getFoodName();
            }
        }
        Consume consume=new Consume();
        consume.setConsumeFood(consumeFood);
        consume.setConsumeCreateTime(new Date());
        consume.setConsumeMoney(totalMoney);
        consume.setUserId(user.getUserId());
        try{
           consumeService.addConsume(consume);
           baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
           return JsonUtils.objectToJson(baseJson);
        }catch (Exception e){
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,"支付失败");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }

    }



}
