package com.sorahjy.buytickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 用于测试
 *
 */

@RestController
public class ClientController {

    //two
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getTicketMsg")
    public String getTicketMsg() {

        //one
        // RestTemplate restTemplate=new RestTemplate();
        // String response = restTemplate.getForObject("http://localhost:10080/tickets/msg",String.class);
        // return response;


        //two
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance=loadBalancerClient.choose("TICKET");
        String url =
            String.format("http://%s:%s/", serviceInstance.getHost(), serviceInstance.getPort() + "/tickets/msg");
        String response = restTemplate.getForObject(url, String.class);


        //利用 RestTemplateConfig
        // String response =restTemplate.getForObject("http://CLIENT1/tickets/msg",String.class);
        // TODO client1

        return response;
    }

}
