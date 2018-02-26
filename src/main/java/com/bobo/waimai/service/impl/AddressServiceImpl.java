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


import com.bobo.waimai.mapper.AddressMapper;
import com.bobo.waimai.pojo.Address;
import com.bobo.waimai.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tianrun-bobo on 2018/2/23/9:52.
 */
@Service
public class AddressServiceImpl implements AddressService  {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAddressesByUserId(Integer userId) {
        return addressMapper.getAddressesByUserId(userId);
    }

    @Override
    public void addAddress(Address address) throws Exception {
        address.setAddressCreateTime(new Date());
        addressMapper.addAddress(address);
    }

    @Override
    public Address getAddressesByAddressId(Integer addressId) {
        return addressMapper.getAddressesByAddressId(addressId);
    }

    @Override
    public List<Address> getAddresses(Integer limit, Integer offset, Integer userId) {
        return addressMapper.getAddresses(limit,offset,userId);
    }

    @Override
    public Long countAll(Integer userId) {
        return addressMapper.countAll(userId);
    }

    @Override
    public void deleteAddress(Integer addressId) throws Exception {
        addressMapper.deleteAddress(addressId);
    }
}
