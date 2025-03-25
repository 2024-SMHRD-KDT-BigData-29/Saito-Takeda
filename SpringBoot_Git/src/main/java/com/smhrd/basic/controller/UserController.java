package com.smhrd.basic.controller;

import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	
	// 회원가입 페이지 출력 요청
    @GetMapping("/user/save")
	public String saveForm() {
		return "userSave";
	}
    
    @PostMapping("/user/save")
    public String save(@RequestParam("userEmail") String userEmail) {
    	System.out.println("UserController.save()실행");
    	System.out.println("userEmail = " + userEmail);
//    	이메일 정보 말고 달느것들도..
    	
    	return null;
    }
    
}