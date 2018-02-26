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


import com.bobo.waimai.mapper.ConsumeMapper;
import com.bobo.waimai.pojo.Consume;
import com.bobo.waimai.pojo.Food;
import com.bobo.waimai.pojo.Order;
import com.bobo.waimai.service.ConsumeService;
import com.bobo.waimai.service.FoodService;
import com.bobo.waimai.service.OrderService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/23/20:31.
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {

    @Autowired
    private ConsumeMapper consumeMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FoodService foodService;
    @Transactional
    @Override
    public void addConsume(Consume consume) throws Exception {
        /**
         * 1，在Consume表中先添加数据
         * 2.在order表中删除数据
         * 3.修改外卖的消费的数量(
         *      首先根据userid查询order表，再次根据order表中的foodid得到food，
         *      使用order中的金额除以food中的单价得到数量，然后更新数量)
         */
        consumeMapper.addConsume(consume);
        List<Order> orders=new ArrayList<>();
        orders=orderService.getOrdersByUserId(consume.getUserId());
        if (orders.size()>0){
            for (int i=0;i<orders.size();i++){
                Order order=orders.get(i);
                Food food = foodService.getFoodById(order.getFoodId());
                Integer foodSaleCount=order.getOrderMoney()/food.getFoodPrice();
                foodService.updateFoodCount(foodSaleCount,food.getFoodId());
            }
        }
        orderService.deleteOrder(consume.getUserId());
    }

    @Override
    public Long countAll(Integer userId) {
        return consumeMapper.countAll(userId);
    }

    @Override
    public List<Consume> getAddresses(Integer limit, Integer offset, Integer userId) {
        return consumeMapper.getAddresses(limit,offset,userId);
    }

    @Override
    public Consume getConsumeById(Integer consumeId) {
        return consumeMapper.getConsumeById(consumeId);
    }

    @Override
    public void updateConsume(Integer consumeId) {
        consumeMapper.updateConsume(consumeId);
    }
}
