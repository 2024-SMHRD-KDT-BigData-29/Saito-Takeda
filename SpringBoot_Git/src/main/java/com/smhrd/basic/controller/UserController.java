package com.smhrd.basic.controller;

import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    
    
    @GetMapping("/")
	public String index() {
		
		return "index"; // .html 
	}
}