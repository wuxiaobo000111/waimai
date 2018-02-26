package com.bobo.waimai.service.impl;

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


import com.bobo.waimai.commons.utils.DateUtils;
import com.bobo.waimai.mapper.FoodMapper;
import com.bobo.waimai.pojo.Discuss;
import com.bobo.waimai.pojo.Food;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.pojo.extend.FoodDiscuss;
import com.bobo.waimai.service.DiscussService;
import com.bobo.waimai.service.FoodService;
import com.bobo.waimai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobo on 2018/2/1/11:18.
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private DiscussService discussService;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;

    @Override
    public Long countAll() {
        return foodMapper.countAll();
    }

    @Override
    public List<Food> getPageFood(Integer limit, Integer offset) {
        return foodMapper.getPageFood(limit,offset);
    }

    @Override
    public Food getFoodById(Integer foodId) {
        return foodMapper.getFoodById(foodId);
    }

    @Transactional
    @Override
    public void addFood(Food food) throws Exception {
        foodMapper.addFood(food);
    }

    @Transactional
    @Override
    public void updateFoodById(Food food) {
        foodMapper.updateFoodById(food);
    }

    @Override
    public void deleteFoodById(Integer foodId) throws Exception {
        foodMapper.deleteFoodById(foodId);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodMapper.getAllFoods();
    }

    @Override
    public List<Food> getFoods(Integer limit, Integer offset, Integer foodTypeId) {
        return foodMapper.getFoods(limit,offset,foodTypeId);
    }

    @Override
    public Food getFoodByFoodName(String foodName) {
        return foodMapper.getFoodByFoodName(foodName);
    }

    @Override
    public void updateFoodCount(Integer foodSaleCount, Integer foodId) {
        foodMapper.updateFoodCount(foodSaleCount,foodId);
    }

    @Transactional
    @Override
    public List<FoodDiscuss> getDisucssByFoodId(Integer foodId) {
       List<Discuss> discusses=new ArrayList<>();
       discusses= discussService.getDiscussByFoodId(foodId);
       List<FoodDiscuss> foodDiscusses=new ArrayList<>(10);
       if (discusses.size()>0){
           for (int i=0;i<discusses.size();i++){
               Discuss discuss=discusses.get(i);
               User user = userService.getUserById(discuss.getUserId());
               Food food = foodService.getFoodById(foodId);
               FoodDiscuss foodDiscuss=new FoodDiscuss();
               foodDiscuss.setUserName(user.getUserName());
               SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
               ParsePosition parsePosition=new ParsePosition(0);
               foodDiscuss.setDiscussCreateTime(simpleDateFormat.parse(discuss.getDiscussCreateTime(),parsePosition));
               foodDiscuss.setDiscussMessage(discuss.getDiscussMessage());
               foodDiscuss.setFoodId(discuss.getFoodId());
               foodDiscuss.setUserId(discuss.getUserId());
               foodDiscuss.setDiscussId(discuss.getDiscussId());
               foodDiscuss.setFood(food);
               foodDiscusses.add(foodDiscuss);
           }
       }
        return foodDiscusses;
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition parsePosition=new ParsePosition(0);
       System.out.println(simpleDateFormat.parse("2018-09-10",parsePosition));
    }
}
