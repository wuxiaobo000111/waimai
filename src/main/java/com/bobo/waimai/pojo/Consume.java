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
import java.util.Date;

/**
 * Created by tianrun-bobo on 2018/2/23/20:21.
 */
public class Consume implements Serializable{
    private Integer consumeId;
    private Date consumeCreateTime;
    private Integer userId;
    private Integer consumeMoney;
    private String  consumeFood;
    private  Integer isDiscuss;

    public Consume(Integer consumeId, Date consumeCreateTime, Integer userId,
                   Integer consumeMoney, String consumeFood, Integer isDiscuss) {
        this.consumeId = consumeId;
        this.consumeCreateTime = consumeCreateTime;
        this.userId = userId;
        this.consumeMoney = consumeMoney;
        this.consumeFood = consumeFood;
        this.isDiscuss = isDiscuss;
    }

    public Consume() {
    }

    @Override
    public String toString() {
        return "Consume{" +
                "consumeId=" + consumeId +
                ", consumeCreateTime=" + consumeCreateTime +
                ", userId=" + userId +
                ", consumeMoney=" + consumeMoney +
                ", consumeFood='" + consumeFood + '\'' +
                ", isDiscuss=" + isDiscuss +
                '}';
    }

    public Integer getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Integer consumeId) {
        this.consumeId = consumeId;
    }

    public Date getConsumeCreateTime() {
        return consumeCreateTime;
    }

    public void setConsumeCreateTime(Date consumeCreateTime) {
        this.consumeCreateTime = consumeCreateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getConsumeFood() {
        return consumeFood;
    }

    public void setConsumeFood(String consumeFood) {
        this.consumeFood = consumeFood;
    }

    public Integer getIsDiscuss() {
        return isDiscuss;
    }

    public void setIsDiscuss(Integer isDiscuss) {
        this.isDiscuss = isDiscuss;
    }
}
