package com.bestnet.hf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/demo")
public class DemoController {
    private static final String REST_URL_PREFIX = "http://hqbService";

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/demo")
    public String demo(){
        return "";
    }


}
