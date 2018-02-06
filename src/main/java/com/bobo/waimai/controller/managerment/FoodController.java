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
import com.bobo.waimai.commons.Consts;
import com.bobo.waimai.commons.DataGridResult;
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.FileUtils;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.commons.utils.UUIDUtils;
import com.bobo.waimai.pojo.Food;
import com.bobo.waimai.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by bobo on 2018/2/1/11:18.
 */
@Controller
@RequestMapping(value = "food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/index.action")
    public ModelAndView getIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("food/index");
        return modelAndView;
    }
    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String FoodList(Integer limit,Integer offset){
        Long total=foodService.countAll();
        List<Food> rows=new ArrayList();
        rows=foodService.getPageFood(limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }

    @RequestMapping(value = {"add.action","edit.action"})
    public ModelAndView addOrEdit(Integer foodId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("food/addOrUpdate");
        Food food=null;
        if (foodId!=null){
            food= foodService.getFoodById(foodId);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("food",food);
        modelAndView.addAllObjects(result);
        return modelAndView;
    }

    @RequestMapping(value = "addFood.action",produces = "text/html;charset=UTF-8")
    public String addFood(Food food, MultipartFile uploadFile, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
//        判断food的id,如果没有id代表着是新增数据，如果存在id，代表着是修改数据
       if (uploadFile.isEmpty()==false){
           String picName= UUIDUtils.getUUID();
           String[] strings = uploadFile.getOriginalFilename().split("\\.");
           String houzhui=strings[1];
           String bashPath=request.getSession().getServletContext().getRealPath("/")+"waimai"+File.separator+
                   "food"+File.separator;
           File distincation= FileUtils.createFile(bashPath,picName,houzhui);
           if (distincation.exists()==false){
               try {
                   distincation.createNewFile();
                   System.err.println(distincation.getPath());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           try {
               uploadFile.transferTo(distincation);
           } catch (IOException e) {
               e.printStackTrace();
           }
           food.setFoodPictureUrl(Consts.SQL_FOOD_PATH+picName+"."+houzhui);
       }
        if (food.getFoodId()==null){
            food.setFoodCreateTime(new Date());
            try {
                foodService.addFood(food);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/food/index.action";
        }else{
            try {
                foodService.updateFoodById(food);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/food/index.action";
        }
    }

    @RequestMapping(value = "/deleteFood.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteFoodById(@RequestParam(value = "foodId",required = true)Integer foodId){
        BaseJson baseJson=null;
        try {
            foodService.deleteFoodById(foodId);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }


    @RequestMapping(value = "deleteMore.action")
    @ResponseBody
    public String deleteUsers(@RequestBody Integer[] ids) throws Exception{
        BaseJson baseJson=null;
        if (ids.length>0){
            for (int i=0;i<ids.length;i++){
                foodService.deleteFoodById(ids[i]);
            }
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        }else {
            return null;
        }
    }

    @RequestMapping(value = "all.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getFoods(){
        BaseJson baseJson=null;
        List<Food> foods=new ArrayList<>();
        foods=foodService.getAllFoods();
        baseJson=new BaseJson(GlobalFianlVar.SUCCESS,foods);
        return JsonUtils.objectToJson(baseJson);
    }
}
