package com.smhrd.basic.controller;

import com.smhrd.basic.entity.User;
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

    //  1. 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String response = userService.register(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //  2. 비밀번호 찾기
    @GetMapping("/find-password")
    public ResponseEntity<String> findPassword(@RequestParam String email) {
        String response = userService.findPassword(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //  3. 회원탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        String response = userService.deleteUser(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/")
	public String index() {
		
		return "index"; // .html 
	}
}