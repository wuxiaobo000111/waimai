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


import com.bobo.waimai.mapper.OrderMapper;
import com.bobo.waimai.pojo.*;
import com.bobo.waimai.pojo.extend.ExtendAddress;
import com.bobo.waimai.pojo.extend.OrderItem;
import com.bobo.waimai.service.AddressService;
import com.bobo.waimai.service.CaritemService;
import com.bobo.waimai.service.FoodService;
import com.bobo.waimai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/23/9:51.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CaritemService caritemService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AddressService addressService;


    @Transactional
    @Override
    public void addOrder(Integer[] ids, Integer addressId, User user) {
        for (int i=0;i<ids.length;i++){
            Food food=foodService.getFoodById(ids[i]);
            CarItem carItem=caritemService.getCarItemByFoodId(ids[i]);
            Integer money=food.getFoodPrice()*carItem.getCaritemNumber();
            Order order=new Order();
            order.setUserId(user.getUserId());
            order.setOrderCreateTime(new Date());
            order.setFoodId(food.getFoodId());
            order.setOrderMoney(money);
            order.setAddressId(addressId);
            orderMapper.addOrder(order);
            caritemService.updateIsAccount(carItem.getCaritemId());
        }
    }

    @Override
    public Long countAll(Integer userId) {
        return orderMapper.countAll(userId);
    }

    @Transactional
    @Override
    public List<OrderItem> getOrders(Integer limit, Integer offset, Integer userId) {
        List<Order> orders=orderMapper.getOrders(limit,offset,userId);
        List<OrderItem>orderItems=new ArrayList<>();
        if (orders.size()>0){
            for (int i=0;i<orders.size();i++){
                Order order=orders.get(i);
                Food food = foodService.getFoodById(order.getFoodId());
                CarItem carItem = caritemService.getCarItemByFoodId(order.getFoodId());
                OrderItem orderItem=new OrderItem();
                orderItem.setFoodCreateTime(new Date());
                orderItem.setFoodPictureUrl(food.getFoodPictureUrl());
                orderItem.setFoodDescription(food.getFoodDescription());
                orderItem.setFoodId(food.getFoodId());
                orderItem.setFoodName(food.getFoodName());
                orderItem.setFoodPrice(food.getFoodPrice());
                orderItem.setFoodSaleCount(food.getFoodSaleCount());
                orderItem.setFoodTypeId(food.getFoodTypeId());
                orderItem.setNumber(carItem.getCaritemNumber());
                orderItem.setMoney(order.getOrderMoney());
                orderItems.add(orderItem);
            }
            return orderItems;
        }else{
            return null;
        }
    }

    @Override
    public ExtendAddress getExtendAddress(Integer userId) {
        Order order= orderMapper.getOrderByUserId(userId);
        Address address=addressService.getAddressesByAddressId(order.getAddressId());
        Long length = orderMapper.countAll(userId);
        List<Order> orders=orderMapper.getOrders( length.intValue(),0,userId);
        Integer money=0;
        if (orders.size()>0) {
            for (int i = 0; i < orders.size(); i++) {
                Order order1 = orders.get(i);
                money += order1.getOrderMoney();
            }
        }

        ExtendAddress extendAddress=new ExtendAddress();
        extendAddress.setTotalMoney(money);
        extendAddress.setAddressCreateTime(new Date());
        extendAddress.setUserId(address.getUserId());
        extendAddress.setAddressId(address.getAddressId());
        extendAddress.setAddressLianxi(address.getAddressLianxi());
        extendAddress.setAddressName(address.getAddressName());
        extendAddress.setAddressPhone(address.getAddressPhone());
        return extendAddress;
    }

    @Override
    public List<Order> getOrder(Integer userId) {
        return orderMapper.getOrder(userId);
    }

    @Override
    public void deleteOrder(Integer userId) {
        orderMapper.deleteOrder(userId);
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderMapper.getOrdersByUserId(userId);
    }
}
