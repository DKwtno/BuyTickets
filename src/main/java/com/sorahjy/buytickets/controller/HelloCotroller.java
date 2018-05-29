package com.sorahjy.buytickets.controller;


import com.sorahjy.buytickets.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nihao")
public class HelloCotroller {

    // @Value("${testValue}")
    // private String testValue;

    @Autowired
    private GirlProperties user;

    @GetMapping(value = "/hello")
    public String say(@RequestParam(value="id",required = false,defaultValue = "0")Integer myId){
        return user.toString()+myId;
    }


}
