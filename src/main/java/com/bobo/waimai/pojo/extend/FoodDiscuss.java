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


import com.bobo.waimai.pojo.Discuss;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tianrun-bobo on 2018/2/24/15:32.
 */
public class FoodDiscuss extends Discuss implements Serializable {
    private String userName;


    public FoodDiscuss() {
    }

    public FoodDiscuss(Integer discussId, Date discussCreateTime,
                       String discussMessage, Integer foodId, Integer userId, String userName) {
        super(discussId, discussCreateTime, discussMessage, foodId, userId);
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "FoodDiscuss{" +
                "userName='" + userName + '\'' +
                "} " + super.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
