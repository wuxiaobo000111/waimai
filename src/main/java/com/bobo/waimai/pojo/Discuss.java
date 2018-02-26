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
 * Created by tianrun-bobo on 2018/2/24/10:35.
 */
public class Discuss implements Serializable {
    private Integer discussId;
    private Date discussCreateTime;
    private String discussMessage;
    private Integer foodId;
    private Integer userId;
    private Food food;

    public void setFood(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public Discuss(Integer discussId, Date discussCreateTime,
                   String discussMessage, Integer foodId, Integer userId) {
        this.discussId = discussId;
        this.discussCreateTime = discussCreateTime;
        this.discussMessage = discussMessage;
        this.foodId = foodId;
        this.userId = userId;
    }

    public Discuss() {
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "discussId=" + discussId +
                ", discussCreateTime=" + discussCreateTime +
                ", discussMessage='" + discussMessage + '\'' +
                ", foodId=" + foodId +
                ", userId=" + userId +
                '}';
    }

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    public String getDiscussCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(this.discussCreateTime);
        return date;
    }

    public void setDiscussCreateTime(Date discussCreateTime) {
        this.discussCreateTime = discussCreateTime;
    }

    public String getDiscussMessage() {
        return discussMessage;
    }

    public void setDiscussMessage(String discussMessage) {
        this.discussMessage = discussMessage;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
