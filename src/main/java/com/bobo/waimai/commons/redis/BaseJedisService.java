package com.bobo.waimai.commons.redis;
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
 * Created by tianrun-bobo on 2018/2/9/10:36.
 * @author tianrun-bobo
 */

import java.util.List;

/**采用不同的方式将数据加载进入redis中去
 * @param <E>
 */
public interface BaseJedisService<E> {

   /**
    * 将所有的数据都加载进入redis
    */
   public void reloadCache();

   /**
    * 将一个对象加入到redis中
    * @param e
    * @param key
    */
   public void insert(E e,String key) throws Exception;

   /**
    * 将一个组合对象加入到redis中去
    * @param list
    * @param key
    */
   public void insert(List<E> list,String key)throws Exception;


}
