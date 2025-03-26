package com.smhrd.basic.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MypageController {
	
	// 생성자 주입
	private final UserService userService;
	
	@GetMapping("/user/mypage")
	public String mypageForm() {
		return "mypage";
	}
	
	@GetMapping("/user/mypage/update")
	public String mypageUpdateForm(HttpSession session, Model model) {
		String myEmail = (String) session.getAttribute("loginEmail");
		UserDTO userDTO = userService.mypageUpdateForm(myEmail);
		model.addAttribute("updateUser", userDTO);
		
		return "mypageUpdate";
	}
	
	@PostMapping("/user/mypage/update")
	public String update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
		
		return "redirect:/user/mypage/"+userDTO.getUserIdx();
	}
	
}
