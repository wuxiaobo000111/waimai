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
 * Created by bobo on 2018/2/3/8:34.
 */
public class News implements Serializable {
    private Integer newsId;
    private Integer newsTypeId;
    private String  newsTitle;
    private String newsUrl;
    private Date newsCreateTime;
    private String newsAuthorName;
    private String newsText;
    private NewsType newsType;

    public News(Integer newsId, Integer newsTypeId, String newsTitle,
                String newsUrl, Date newsCreateTime, String newsAuthorName, String newsText) {
        this.newsId = newsId;
        this.newsTypeId = newsTypeId;
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsCreateTime = newsCreateTime;
        this.newsAuthorName = newsAuthorName;
        this.newsText = newsText;
    }

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsTypeId=" + newsTypeId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                ", newsCreateTime=" + newsCreateTime +
                ", newsAuthorName='" + newsAuthorName + '\'' +
                ", newsText='" + newsText + '\'' +
                ", newsType=" + newsType +
                '}';
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsTypeId() {
        return newsTypeId;
    }

    public void setNewsTypeId(Integer newsTypeId) {
        this.newsTypeId = newsTypeId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsCreateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date= dateFormat.format(this.newsCreateTime);
        return date;
    }

    public void setNewsCreateTime(Date newsCreateTime) {
        this.newsCreateTime = newsCreateTime;
    }

    public String getNewsAuthorName() {
        return newsAuthorName;
    }

    public void setNewsAuthorName(String newsAuthorName) {
        this.newsAuthorName = newsAuthorName;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }
}
