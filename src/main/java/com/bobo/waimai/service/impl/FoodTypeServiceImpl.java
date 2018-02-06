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


import com.bobo.waimai.mapper.FoodTypeMapper;
import com.bobo.waimai.pojo.FoodType;
import com.bobo.waimai.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bobo on 2018/2/1/17:44.
 */
@Service
public class FoodTypeServiceImpl implements FoodTypeService {
    @Autowired
    private FoodTypeMapper foodTypeMapper;

    @Override
    public Long countAll() {
        return foodTypeMapper.countAll();
    }

    @Override
    public List<FoodType> getPageFoodType(Integer limit, Integer offset) {
        return foodTypeMapper.getPageFoodType(limit,offset);
    }

    @Override
    public List<FoodType> getPageFoodTypeSearch(String searchName, Integer limit, Integer offset) {
        return foodTypeMapper.getPageFoodTypeSearch(searchName,limit,offset);
    }

    @Override
    public Long countAllSearch(String searchName) {
        return foodTypeMapper.countAllSearch(searchName);
    }

    @Override
    public FoodType getFoodTypeById(Integer foodTypeId) {
        return foodTypeMapper.getFoodTypeById(foodTypeId);
    }

    @Transactional
    @Override
    public void updateUserById(FoodType foodType) {
        foodTypeMapper.updateUserById(foodType);
    }
    @Transactional
    @Override
    public void addFoodType(FoodType foodType) {
        foodTypeMapper.addFoodType(foodType);
    }

    @Override
    public List<FoodType> getAll() {
        return foodTypeMapper.getAll();
    }
}
