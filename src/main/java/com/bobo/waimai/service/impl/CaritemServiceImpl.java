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


import com.bobo.waimai.mapper.CaritemMapper;
import com.bobo.waimai.pojo.CarItem;
import com.bobo.waimai.service.CaritemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/22/17:26.
 */
@Service
public class CaritemServiceImpl implements CaritemService {
    @Autowired
    private CaritemMapper caritemMapper;

    @Override
    public void addCarItem(CarItem carItem) throws Exception {
        carItem.setCaritemCreateTime(new Date());
        CarItem flag=caritemMapper.isExits(carItem.getFoodId());
        if (flag.getIsAccount()==1){
            caritemMapper.updateCarItem(carItem.getFoodId(),carItem.getCaritemNumber());
        }else{
            caritemMapper.addCarItem(carItem);
        }

    }

    @Override
    public Long countAll(Integer userId) {
        return caritemMapper.countAll(userId);
    }

    @Override
    public List<CarItem> getItems(Integer limit, Integer offset, Integer userId) {
        return caritemMapper.getItems(limit,offset,userId);
    }

    @Override
    public CarItem getCarItemByFoodId(Integer foodId) {
        return caritemMapper.getCarItemByFoodId(foodId);
    }

    @Override
    public void updateIsAccount(Integer caritemId) {
        caritemMapper.updateIsAccount(caritemId);
    }
}
