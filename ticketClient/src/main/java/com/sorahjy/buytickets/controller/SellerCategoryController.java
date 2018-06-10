package com.sorahjy.buytickets.controller;

import com.sorahjy.buytickets.dataobject.AirlineInfo;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.form.CategoryForm;
import com.sorahjy.buytickets.service.AirlineInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private AirlineInfoService airlineInfoService;


    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<AirlineInfo> airlineInfoList=airlineInfoService.findAll();
        map.put("airlineInfoList", airlineInfoList);

        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "airlineId",required = false)Integer airlineId,Map<String,Object> map){

        if (airlineId !=null) {

            AirlineInfo airlineInfo= airlineInfoService.findOne(airlineId);
            map.put("airlineInfo",airlineInfo);


        }
        // else {
        //     map.put("airlineInfo", new AirlineInfo());
        // }
        return new ModelAndView("category/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult,Map<String,Object> map){

        if (bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/tickets/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        AirlineInfo airlineInfo =new AirlineInfo();
        try {


            if (form.getAirlineId() != null) {
                airlineInfo = airlineInfoService.findOne(form.getAirlineId());
            }
            BeanUtils.copyProperties(form, airlineInfo);

            airlineInfoService.save(airlineInfo);
        }catch (SellException e){
            map.put("msg", "添加类目失败");
            map.put("url", "/tickets/seller/category/index");
            return new ModelAndView("common/error",map);

        }
        map.put("url", "/tickets/seller/category/list");
        return new ModelAndView("common/success", map);
    }


}
