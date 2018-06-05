package com.sorahjy.buytickets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试
 *
 */


@RestController
public class ServerController {
    @GetMapping("/msg")
    public String msg(){
        return "this is a msg 2";
    }
}
