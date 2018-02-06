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
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bobo on 2018/2/1/17:45.
 */
@Controller
@RequestMapping("foodType")
public class FoodTypeController {

    @Autowired
    private FoodTypeService foodTypeService;

    @RequestMapping(value = "/index.action")
    public ModelAndView getIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("food/type/index");
        return modelAndView;
    }

    @RequestMapping(value = "list.action",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String foodTypeList(Integer limit,Integer offset) {
        Long total=foodTypeService.countAll();
        List<FoodType> rows=new ArrayList();
        rows=foodTypeService.getPageFoodType(limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = "search.action")
    @ResponseBody
    public String search(@RequestParam(name = "searchName")String searchName,Integer limit,Integer offset){
        Long total=foodTypeService.countAllSearch(searchName);
        List<FoodType> rows=new ArrayList();
        rows=foodTypeService.getPageFoodTypeSearch(searchName,limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = {"edit.action","add.action"})
    public ModelAndView addOrEdit(Integer foodTypeId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("food/type/addOrUpdate");
        FoodType foodType=null;
        if (foodTypeId!=null){
            foodType= foodTypeService.getFoodTypeById(foodTypeId);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("foodType",foodType);
        modelAndView.addAllObjects(result);
        return modelAndView;
    }

    @RequestMapping(value = "all.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAll(){
        BaseJson baseJson=null;

        List<FoodType> foodTypes=new ArrayList<>();
        foodTypes= foodTypeService.getAll();
        baseJson=new BaseJson(GlobalFianlVar.SUCCESS,foodTypes);
        return JsonUtils.objectToJson(baseJson);
    }

    @RequestMapping(value = "addFoodType.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addUser(@RequestBody FoodType foodType){
        BaseJson baseJson=null;
        try {
            foodTypeService.addFoodType(foodType);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            e.printStackTrace();
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "updateFoodType.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updadteUser(@RequestBody FoodType foodType){
        BaseJson baseJson=null;
        try {
            foodTypeService.updateUserById(foodType);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            e.printStackTrace();
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            return JsonUtils.objectToJson(baseJson);
        }
    }

}
