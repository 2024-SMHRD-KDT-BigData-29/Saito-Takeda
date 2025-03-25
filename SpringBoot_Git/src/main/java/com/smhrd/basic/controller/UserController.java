package com.smhrd.basic.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;
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
    
//    회원가입처리
    @PostMapping("/user/save")
    public String save(@ModelAttribute UserDTO userDTO) {
    	System.out.println("UserController.save()실행");
    	System.out.println("userDTO = " + userDTO);   	
    	
    	userService.save(userDTO);
    	
    	return "login";
    }
    
//    로그인페이지 이동
    @GetMapping("/member/login")
    public String loginForm() {
    	
    	return "login";
    }
    
//    로그인 이후 처리
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {
    	UserDTO loginResult = userService.login(userDTO);
    	if(loginResult != null) {
    		// login 성공
    		
    		session.setAttribute("loginEmail", loginResult.getUserEmail());
    		return "main";
    	}else {
    		// login 실패
    		return "login";
    	}
    	
    }
    
    @GetMapping("/member/")
    public String findAll(Model model) {
    	List<UserDTO> userDTOList = userService.findAll();
//    	어떠한 html로 가져갈 데이터가 있다면 model 사용
//    	변수명은 html thymeleaf에 명시된 변수명 사용
    	model.addAttribute("userList", userDTOList);
    	
    	
    	return "list";
    }
    
    @GetMapping("/member/{idx}")
    public String findByEmail(@PathVariable Integer idx, Model model) {
    	UserDTO userDTO = userService.findByEmail(idx);
    	model.addAttribute("user", userDTO);
    	
    	return "userDetail";
    }
    
    
    
}