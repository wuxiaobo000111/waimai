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


import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Created by tianrun-bobo on 2018/2/8/14:00.
 */
public class JedisService {

    @Resource(name = "redisTemplate")
    private  RedisTemplate redisTemplate;


    public void deleteString(String key){
        redisTemplate.delete(key);
    }

    public void insertString(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.opsForGeo();
    }

    public String getStringValue(String key){
        String value = (String) redisTemplate.opsForValue().get(key);
        return value;
    }

    public void insertHash(String hash,String field,String value){
        redisTemplate.opsForHash().put(hash,field,value);
    }

}
