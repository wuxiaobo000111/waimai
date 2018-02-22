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
 * Created by tianrun-bobo on 2018/2/22/8:13.
 */

public class Feedback implements Serializable{
    private Integer feedbackId;
    private String feedbackName;
    private Date feedbackCreateTime;
    private String feedbackMessage;
    private Integer  userId;
    private User user;

    public Feedback(Integer feedbackId, String feedbackName,
                    Date feedbackCreateTime, String feedbackMessage, Integer userId, User user) {
        this.feedbackId = feedbackId;
        this.feedbackName = feedbackName;
        this.feedbackCreateTime = feedbackCreateTime;
        this.feedbackMessage = feedbackMessage;
        this.userId = userId;
        this.user = user;
    }

    public Feedback() {
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "feedbackId=" + feedbackId +
                ", feedbackName='" + feedbackName + '\'' +
                ", feedbackCreateTime=" + feedbackCreateTime +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                '}';
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public String getFeedbackCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(this.feedbackCreateTime);
        return format;
    }

    public void setFeedbackCreateTime(Date feedbackCreateTime) {
        this.feedbackCreateTime = feedbackCreateTime;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
