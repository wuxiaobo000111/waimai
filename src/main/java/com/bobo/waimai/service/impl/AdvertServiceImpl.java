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


import com.bobo.waimai.mapper.AdvertMapper;
import com.bobo.waimai.pojo.Advert;
import com.bobo.waimai.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bobo on 2018/2/2/13:55.
 */
@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertMapper advertMapper;

    @Override
    public Long countAll() {
        return advertMapper.countAll();
    }

    @Override
    public List<Advert> getPageAdvert(Integer limit, Integer offset) {
        return advertMapper.getPageAdvert(limit,offset);
    }

    @Override
    public Advert getAdvertById(Integer advertId) {
        return advertMapper.getAdvertById(advertId);
    }

    @Transactional
    @Override
    public void updateAdvertById(Advert advert) {
        advertMapper.updateAdvertById(advert);
    }

    @Transactional
    @Override
    public void addAdvert(Advert advert) {
        advertMapper.addAdvert(advert);
    }

    @Override
    public void deleteFoodById(Integer advertId)throws Exception{
        advertMapper.deleteFoodById(advertId);
    }

    @Override
    public List<Advert> getAllAdverts() throws Exception {
        return advertMapper.getAllAdverts();
    }

    @Override
    public List<Advert> getAllAdvertsWithShow() {
        return advertMapper.getAllAdvertsWithShow();
    }
}
