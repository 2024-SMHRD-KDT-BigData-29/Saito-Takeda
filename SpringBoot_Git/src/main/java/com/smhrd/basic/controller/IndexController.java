package com.smhrd.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	// 기본페이지 요청 메소드 
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
