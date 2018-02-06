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
 * Created by bobo on 2018/2/1/10:43.
 */
public class Food implements Serializable {
    private Integer foodId;
    private Integer foodPrice;
    private String foodName;
    private Integer foodSaleCount;
    private Date foodCreateTime;
    private String foodPictureUrl;
    private String foodDescription;
    private Integer foodTypeId;

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public Food(Integer foodId, Integer foodPrice, String foodName,
                Integer foodSaleCount, Date foodCreateTime, String foodPictureUrl, String foodDescription) {
        this.foodId = foodId;
        this.foodPrice = foodPrice;
        this.foodName = foodName;
        this.foodSaleCount = foodSaleCount;
        this.foodCreateTime = foodCreateTime;
        this.foodPictureUrl = foodPictureUrl;
        this.foodDescription = foodDescription;
    }

    public Food() {
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodPrice=" + foodPrice +
                ", foodName='" + foodName + '\'' +
                ", foodSaleCount=" + foodSaleCount +
                ", foodCreateTime=" + foodCreateTime +
                ", foodPictureUrl='" + foodPictureUrl + '\'' +
                ", foodDescription='" + foodDescription + '\'' +
                '}';
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodSaleCount() {
        return foodSaleCount;
    }

    public void setFoodSaleCount(Integer foodSaleCount) {
        this.foodSaleCount = foodSaleCount;
    }

    public String getFoodCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=dateFormat.format(this.foodCreateTime);
        return date;
    }

    public void setFoodCreateTime(Date foodCreateTime) {
        this.foodCreateTime = foodCreateTime;
    }

    public String getFoodPictureUrl() {
        return foodPictureUrl;
    }

    public void setFoodPictureUrl(String foodPictureUrl) {
        this.foodPictureUrl = foodPictureUrl;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
