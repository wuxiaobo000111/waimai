package com.bobo.waimai.controller.managerment;

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


import com.bobo.waimai.commons.BaseJson;
import com.bobo.waimai.commons.Consts;
import com.bobo.waimai.commons.DataGridResult;
import com.bobo.waimai.commons.GlobalFianlVar;
import com.bobo.waimai.commons.utils.FileUtils;
import com.bobo.waimai.commons.utils.JsonUtils;
import com.bobo.waimai.commons.utils.UUIDUtils;
import com.bobo.waimai.pojo.Advert;
import com.bobo.waimai.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by bobo on 2018/2/2/13:55.
 */
@Controller
@RequestMapping(value = "advert")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @RequestMapping(value = "/index.action")
    public ModelAndView getIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("advert/index");
        return modelAndView;
    }

    @RequestMapping(value = "/list.action",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String AdvertList(Integer limit,Integer offset){
        Long total=advertService.countAll();
        List<Advert> rows=new ArrayList();
        rows=advertService.getPageAdvert(limit,offset);
        DataGridResult result=new DataGridResult(rows,total);
        return JsonUtils.objectToJson(result);
    }


    @RequestMapping(value = {"add.action","edit.action"})
    public ModelAndView addOrEdit(Integer advertId){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("advert/addOrUpdate");
        Advert advert=null;
        if (advertId!=null){
            advert= advertService.getAdvertById(advertId);
        }
        Map<String,Object> result=new HashMap<>();
        result.put("advert",advert);
        modelAndView.addAllObjects(result);
        return modelAndView;
    }

    @RequestMapping(value = "/addAdvert.action",produces = "text/html;charset=UTF-8")
    public String addFood(Advert advert, MultipartFile uploadFile, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
//        判断food的id,如果没有id代表着是新增数据，如果存在id，代表着是修改数据
        if (uploadFile.isEmpty()==false){
            String picName= UUIDUtils.getUUID();
            String[] strings = uploadFile.getOriginalFilename().split("\\.");
            String houzhui=strings[1];
            String bashPath=request.getSession().getServletContext().getRealPath("/")+"waimai"+File.separator+
                    "advert"+File.separator;
            File distincation= FileUtils.createFile(bashPath,picName,houzhui);
            if (distincation.exists()==false){
                try {
                    distincation.createNewFile();
                    System.err.println(distincation.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                uploadFile.transferTo(distincation);
            } catch (IOException e) {
                e.printStackTrace();
            }
            advert.setAdvertPictureUrl(Consts.BASE_ADVERT_PATH+picName+"."+houzhui);
        }
        if (advert.getAdvertId()==null){
            advert.setAdvertCreateTime(new Date());
            try {
                advertService.addAdvert(advert);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/advert/index.action";
        }else{
            try {
                advertService.updateAdvertById(advert);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/advert/index.action";
        }

    }

    @RequestMapping(value = "deleteAdvert.action",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteAdvertById(@RequestParam(value = "advertId",required = true)Integer advertId){
        BaseJson baseJson=null;
        try {
            advertService.deleteFoodById(advertId);
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        } catch (Exception e) {
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }

    @RequestMapping(value = "/deleteMore.action")
    @ResponseBody
    public String deleteAdverts(@RequestBody Integer[] ids) throws Exception{
        BaseJson baseJson=null;
        if (ids.length>0){
            for (int i=0;i<ids.length;i++){
                advertService.deleteFoodById(ids[i]);
            }
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,null);
            return JsonUtils.objectToJson(baseJson);
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/getAdverts.action")
    @ResponseBody
    public String getAll() throws Exception{
        BaseJson baseJson=null;
        List<Advert> adverts=new ArrayList<>();
        try {
            adverts=advertService.getAllAdverts();
            baseJson=new BaseJson(GlobalFianlVar.SUCCESS,adverts);
            return JsonUtils.objectToJson(baseJson);
        }catch (Exception e){
            baseJson=new BaseJson(GlobalFianlVar.ERROR,"服务器内部发生错误");
            e.printStackTrace();
            return JsonUtils.objectToJson(baseJson);
        }
    }
}
