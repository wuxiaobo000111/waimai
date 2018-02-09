package com.bobo.waimai.service.impl;

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


import com.bobo.waimai.commons.redis.JedisService;
import com.bobo.waimai.mapper.NewsTypeMapper;
import com.bobo.waimai.pojo.NewsType;
import com.bobo.waimai.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bobo on 2018/2/2/17:30.
 */
@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Resource(name = "jedisService")
    private JedisService jedisService;

    @Override
    public Long countAll() {
        return newsTypeMapper.countAll();
    }

    @Override
    public List<NewsType> getPageNewsType(Integer limit, Integer offset) {
        /**
         * 先判断在redis中有没有，如果有的话从redis中取出，如果没有的话，则在数据库中
         * 进行查询，当查到结果的时候，首先存在redis中，然后展示结果数据
         */
//        List<NewsType> newsTypes = (List<NewsType>) jedisService.getListInJedis("newsTypes");
//        if (newsTypes!=null){
//            return newsTypes;
//        }
//        else{
            List<NewsType> types =newsTypeMapper.getPageNewsType(limit,offset);
            jedisService.addListToJedis("newsTypes",types);
            return types;
//        }
    }

    @Override
    public NewsType getNewsTypeById(Integer newsTypeId) {
        return newsTypeMapper.getNewsTypeById(newsTypeId);
    }

    @Transactional
    @Override
    public void addNewsType(NewsType newsType) {
        newsTypeMapper.addNewsType(newsType);
    }

    @Transactional
    @Override
    public void updateNeById(NewsType newsType) {
        newsTypeMapper.updateNeById(newsType);
    }

    @Override
    public List<NewsType> getAllNewsTypes() {
        return newsTypeMapper.getAllNewsTypes();
    }
}
