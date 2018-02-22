package com.bobo.waimai.pojo.extend;

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

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tianrun-bobo on 2018/2/22/21:56.
 */
public class ExtendFood extends Food implements Serializable {
    private Integer caritemNumber;

    public void setCaritemNumber(Integer caritemNumber) {
        this.caritemNumber = caritemNumber;
    }

    public Integer getCaritemNumber() {
        return caritemNumber;
    }

    public ExtendFood(Integer foodId, Integer foodPrice, String foodName,
                      Integer foodSaleCount, Date foodCreateTime, String foodPictureUrl,
                      String foodDescription, Integer caritemNumber) {
        super(foodId, foodPrice, foodName, foodSaleCount, foodCreateTime, foodPictureUrl, foodDescription);
        this.caritemNumber = caritemNumber;
    }

    public ExtendFood(){}

    @Override
    public String toString() {
        return "ExtendFood{" +
                "caritemNumber=" + caritemNumber +
                "} " + super.toString();
    }
}
