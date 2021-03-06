package com.bobo.waimai.service;
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


import com.bobo.waimai.pojo.Food;
import com.bobo.waimai.pojo.extend.FoodDiscuss;

import java.util.List;

/**
 * Created by bobo on 2018/2/1/11:17.
 */
public interface FoodService {
    public Long countAll();

    public List<Food> getPageFood(Integer limit, Integer offset);

    public Food getFoodById(Integer foodId);

    public void addFood(Food food) throws Exception;

    public void updateFoodById(Food food);

    public void deleteFoodById(Integer foodId) throws Exception;

    public List<Food> getAllFoods();


    public List<Food> getFoods(Integer limit, Integer offset, Integer foodTypeId);

    public Food getFoodByFoodName(String foodName);

    public void updateFoodCount(Integer foodSaleCount,Integer foodId);

    public List<FoodDiscuss> getDisucssByFoodId(Integer foodId);
}
