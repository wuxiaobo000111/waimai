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
import com.bobo.waimai.pojo.Address;
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.service.AddressService;
import com.bobo.waimai.service.FoodService;
import com.bobo.waimai.service.FoodTypeService;
import com.bobo.waimai.service.NewsTypeService;
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
 * Created by tianrun-bobo on 2018/2/23/9:53.
 */
@Controller
@RequestMapping(value = "qiantaiAddress")
public class QiantaiAddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private NewsTypeService newsTypeService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private FoodService foodService;
    @RequestMapping(value = "list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String list(HttpServletRequest request){
        BaseJson baseJson=null;
        User user= (User) request.getSession().getAttribute("user");
        List<Address> addresses=new ArrayList<>();
        addresses=addressService.getAddressesByUserId(user.getUserId());
        if (addresses.size()>0){
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,addresses);
        }else {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"您还没有地址，请添加一个新地址");
        }
        return JsonUtils.objectToJson(baseJson);
    }

    @RequestMapping(value = "index.action")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("qiantai/addAddress");
        List<NewsType> newsTypes=new ArrayList<>();
        newsTypes = newsTypeService.getAllNewsTypes();
        modelAndView.addObject("newsTypes",newsTypes);
        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes=foodTypeService.getAll();
        modelAndView.addObject("foodTypes",foodTypes);
        return modelAndView;
    }

    @RequestMapping(value = "add.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addAddress(@RequestBody Address address,HttpServletRequest request){
        BaseJson baseJson=null;
        User user= (User) request.getSession().getAttribute("user");
        address.setUserId(user.getUserId());
        try {
            addressService.addAddress(address);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,"添加成功");
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"添加失败，请重新添加");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "/listAddresses.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addresses(Integer limit,Integer offset,HttpServletRequest request){

        User user= (User) request.getSession().getAttribute("user");
        Long total=addressService.countAll(user.getUserId());
        List<Address> rows=new ArrayList();
        rows=addressService.getAddresses(limit,offset,user.getUserId());
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = "deleteAddress.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteAddress(Integer addressId){
        BaseJson baseJson=null;
        try {
            addressService.deleteAddress(addressId);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,"删除成功");
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"删除失败");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }
}
