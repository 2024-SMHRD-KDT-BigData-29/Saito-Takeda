package com.smhrd.basic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	// 생성자 주입 어노테이션
public class UserController {
	// 생성자 주입
	private final UserService userService;
	
	
	
	// 회원가입 페이지 출력 요청
    @GetMapping("/user/save")
	public String saveForm() {
		return "userSave";
	}
    
    @PostMapping("/user/save")
    public String save(@ModelAttribute UserDTO userDTO) {
    	System.out.println("UserController.save()실행");
    	System.out.println("userDTO = " + userDTO);   	
    	
    	userService.save(userDTO);
    	
    	return "login";
    }
    
}