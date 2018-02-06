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
 * Created by bobo on 2018/2/2/13:49.
 */
public class Advert implements Serializable {
    private Integer advertId;
    private String advertName;
    private Date advertCreateTime;
    private String advertPictureUrl;
    private Integer foodId;
    private String advertDescription;
    private String advertButtonText;
    private Integer advertShow;
    private Food food;

    public void setFood(Food food) {
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public Advert(Integer advertId, String advertName, Date advertCreateTime, String advertPictureUrl, Integer foodId,
                  String advertDescription, String advertButtonText, Integer advertShow) {
        this.advertId = advertId;
        this.advertName = advertName;
        this.advertCreateTime = advertCreateTime;
        this.advertPictureUrl = advertPictureUrl;
        this.foodId = foodId;
        this.advertDescription = advertDescription;
        this.advertButtonText = advertButtonText;
        this.advertShow = advertShow;
    }

    public Advert() {
    }

    @Override
    public String toString() {
        return "Advert{" +
                "advertId=" + advertId +
                ", advertName='" + advertName + '\'' +
                ", advertCreateTime=" + advertCreateTime +
                ", advertPictureUrl='" + advertPictureUrl + '\'' +
                ", foodId=" + foodId +
                ", advertDescription='" + advertDescription + '\'' +
                ", advertButtonText='" + advertButtonText + '\'' +
                ", advertShow=" + advertShow +
                '}';
    }

    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getAdvertCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(this.advertCreateTime);
        return date;
    }

    public void setAdvertCreateTime(Date advertCreateTime) {
        this.advertCreateTime = advertCreateTime;
    }

    public String getAdvertPictureUrl() {
        return advertPictureUrl;
    }

    public void setAdvertPictureUrl(String advertPictureUrl) {
        this.advertPictureUrl = advertPictureUrl;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getAdvertDescription() {
        return advertDescription;
    }

    public void setAdvertDescription(String advertDescription) {
        this.advertDescription = advertDescription;
    }

    public String getAdvertButtonText() {
        return advertButtonText;
    }

    public void setAdvertButtonText(String advertButtonText) {
        this.advertButtonText = advertButtonText;
    }

    public Integer getAdvertShow() {
        return advertShow;
    }

    public void setAdvertShow(Integer advertShow) {
        this.advertShow = advertShow;
    }
}
