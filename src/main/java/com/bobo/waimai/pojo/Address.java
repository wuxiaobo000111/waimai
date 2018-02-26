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
 * Created by tianrun-bobo on 2018/2/23/9:47.
 */
public class Address implements Serializable {
    private Integer addressId;
    private String addressLianxi;
    private String addressPhone;
    private String addressName;
    private Integer userId;
    private Date addressCreateTime;
    private User user;

    public Address(Integer addressId, String addressLianxi, String addressPhone,
                   String addressName, Integer userId, Date addressCreateTime, User user) {
        this.addressId = addressId;
        this.addressLianxi = addressLianxi;
        this.addressPhone = addressPhone;
        this.addressName = addressName;
        this.userId = userId;
        this.addressCreateTime = addressCreateTime;
        this.user = user;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressLianxi='" + addressLianxi + '\'' +
                ", addressPhone='" + addressPhone + '\'' +
                ", addressName='" + addressName + '\'' +
                ", userId=" + userId +
                ", addressCreateTime=" + addressCreateTime +
                ", user=" + user +
                '}';
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressLianxi() {
        return addressLianxi;
    }

    public void setAddressLianxi(String addressLianxi) {
        this.addressLianxi = addressLianxi;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddressCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(this.addressCreateTime);
        return date;
    }

    public void setAddressCreateTime(Date addressCreateTime) {
        this.addressCreateTime = addressCreateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
