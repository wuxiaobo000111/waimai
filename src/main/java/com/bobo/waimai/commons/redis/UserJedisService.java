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


import com.bobo.waimai.commons.Consts;
import com.bobo.waimai.mapper.UserMapper;
import com.bobo.waimai.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/9/13:44.
 * @author tianrun-bobo
 * @see com.bobo.waimai.commons.redis.BaseJedisService
 */
@Service
public class UserJedisService implements BaseJedisService<User>{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void reloadCache() {
        List<User> users = userMapper.getUsers();
        if (redisTemplate.hasKey(Consts.USER_IN_REDIS))
            return;
        else {
            redisTemplate.opsForList().leftPushAll(Consts.USER_IN_REDIS, users);
            redisTemplate.expire(Consts.USER_IN_REDIS,1800000, null);
        }
    }

    @Override
    public void insert(User user, String key) throws Exception {
        /**
         * 实现思想，首先判断在redis中这个值是否存在是否已经存在，如果存在，则跳出方法，
         * 如果不存在，则执行插入操作。
         */
        Long length= (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Long length=connection.lLen(key.getBytes());
                return length;
            }
        });
        List<User> users = redisTemplate.opsForList().range(key, 0, length);
        if (users.contains(user)==false){
            redisTemplate.opsForList().leftPush(key,user);
        }

    }

    @Override
    public void insert(List<User> list, String key) throws Exception {
        /**
         *
         */
        Long length= (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Long length=connection.lLen(key.getBytes());
                return length;
            }
        });
        List<User> users = redisTemplate.opsForList().range(key, 0, length);
        for (int i=0;i<list.size();i++){
            if (users.contains(list.get(i))==false){
                redisTemplate.opsForList().leftPush(key,list.get(i));
            }
        }
    }
}
