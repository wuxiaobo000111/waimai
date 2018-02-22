package com.bobo.waimai.pojo;

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


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianrun-bobo on 2018/2/22/17:21.
 */
public class CarItem implements Serializable {
    private Integer caritemId;
    private Date caritemCreateTime;
    private Integer caritemNumber;
    private Integer userId;
    private Integer foodId;
    private Integer caritemBrought;

    public CarItem(Integer caritemId, Date caritemCreateTime, Integer caritemNumber,
                   Integer userId, Integer foodId, Integer caritemBrought) {
        this.caritemId = caritemId;
        this.caritemCreateTime = caritemCreateTime;
        this.caritemNumber = caritemNumber;
        this.userId = userId;
        this.foodId = foodId;
        this.caritemBrought = caritemBrought;
    }

    public CarItem() {
    }

    public Integer getCaritemId() {
        return caritemId;
    }

    public void setCaritemId(Integer caritemId) {
        this.caritemId = caritemId;
    }

    public String getCaritemCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(this.caritemCreateTime);
        return date;
    }

    public void setCaritemCreateTime(Date caritemCreateTime) {
        this.caritemCreateTime = caritemCreateTime;
    }

    public Integer getCaritemNumber() {
        return caritemNumber;
    }

    public void setCaritemNumber(Integer caritemNumber) {
        this.caritemNumber = caritemNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getCaritemBrought() {
        return caritemBrought;
    }

    public void setCaritemBrought(Integer caritemBrought) {
        this.caritemBrought = caritemBrought;
    }

    @Override
    public String toString() {
        return "CarItem{" +
                "caritemId=" + caritemId +
                ", caritemCreateTime=" + caritemCreateTime +
                ", caritemNumber=" + caritemNumber +
                ", userId=" + userId +
                ", foodId=" + foodId +
                ", caritemBrought=" + caritemBrought +
                '}';
    }
}
