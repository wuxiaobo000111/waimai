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


import com.bobo.waimai.commons.DiscussResult;
import com.bobo.waimai.mapper.DiscussMapper;
import com.bobo.waimai.pojo.Discuss;
import com.bobo.waimai.pojo.Food;
import com.bobo.waimai.pojo.User;
import com.bobo.waimai.service.ConsumeService;
import com.bobo.waimai.service.DiscussService;
import com.bobo.waimai.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/24/10:44.
 */
@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private ConsumeService consumeService;
    @Transactional
    @Override
    public void addDiscuss(DiscussResult discussResult, User user) {
        /**
         * 逻辑：
         * 1，首先得到了食物名称，然后对食物名称进行拆分，
         * 得到食物名称之后，根据食物名称查询到食物对应的ID，
         * 然后对评论进行插入操作
         */

        String food=discussResult.getConsumeFood();
        String[] foods = food.split("-");
        for (int i=0;i<foods.length;i++){
            Food getFood=foodService.getFoodByFoodName(foods[i]);
            Discuss discuss=new Discuss();
            discuss.setDiscussCreateTime(new Date());
            discuss.setDiscussMessage(discussResult.getDiscussMessage());
            discuss.setFoodId(getFood.getFoodId());
            discuss.setUserId(user.getUserId());
            discussMapper.addDiscuss(discuss);
            consumeService.updateConsume(discussResult.getConsumeId());
        }
    }

    @Override
    public Long countAll(Integer userId) {
        return discussMapper.countAll(userId);
    }

    @Override
    public List<Discuss> discusses(Integer limit, Integer offset, Integer userId) {
        return discussMapper.discusses(limit,offset,userId);
    }

    @Override
    public List<Discuss> getDiscussByFoodId(Integer foodId) {
        return discussMapper.getDiscussByFoodId(foodId);
    }


}
