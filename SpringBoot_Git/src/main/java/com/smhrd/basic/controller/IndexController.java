package com.smhrd.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 첫 화면 컨트롤러 ( 로그인과, 회원가입 )

@Controller
public class IndexController {
	
	// 기본페이지 요청 메소드 
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
