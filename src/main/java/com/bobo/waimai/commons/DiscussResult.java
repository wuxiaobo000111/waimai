package com.bobo.waimai.commons;

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

/**
 * Created by tianrun-bobo on 2018/2/24/11:10.
 */
public class DiscussResult implements Serializable {
    private Integer consumeId;
    private Integer consumeMoney;
    private String consumeFood;
    private String discussMessage;


    public DiscussResult(Integer consumeId, Integer consumeMoney,
                         String consumeFood, String discussMessage) {
        this.consumeId = consumeId;
        this.consumeMoney = consumeMoney;
        this.consumeFood = consumeFood;
        this.discussMessage = discussMessage;
    }

    public DiscussResult() {
    }

    @Override
    public String toString() {
        return "DiscussResult{" +
                "consumeId=" + consumeId +
                ", consumeMoney=" + consumeMoney +
                ", consumeFood='" + consumeFood + '\'' +
                ", discussMessage='" + discussMessage + '\'' +
                '}';
    }

    public Integer getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Integer consumeId) {
        this.consumeId = consumeId;
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

    public String getDiscussMessage() {
        return discussMessage;
    }

    public void setDiscussMessage(String discussMessage) {
        this.discussMessage = discussMessage;
    }


}
