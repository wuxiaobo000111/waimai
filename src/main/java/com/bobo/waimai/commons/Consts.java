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


/**
 * Created by bobo on 2018/1/30/15:54.
 */
//常量类
public class Consts {
//    管理员用户为2
    public static final Integer USER_TYPE_MANAGER=2;
//    普通用户为1
    public static final Integer USER_TYPE_PUTONG=1;
    public static final  String BASE_PATH="G:\\learnProject\\waimai\\src\\main\\webapp\\waimai\\food\\";
//    在数据库中存储中的食物照片的相对路径
    public static final String SQL_FOOD_PATH="/waimai/food/";

    public static final String BASE_ADVERT_PATH="/waimai/advert/";

    public static final String BASE_NEWS_PATH="/waimai/news/";

//    设置user表在redis中的key值
    public static final String USER_IN_REDIS="users";
}
