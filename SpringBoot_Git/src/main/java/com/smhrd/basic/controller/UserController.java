package com.smhrd.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	
	// 회원가입 페이지 출력 요청
    @GetMapping("/user/save")
	public String index() {
		
		return "userSave";
	}
}