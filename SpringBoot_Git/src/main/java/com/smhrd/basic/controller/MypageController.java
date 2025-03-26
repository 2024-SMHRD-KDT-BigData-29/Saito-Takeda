package com.smhrd.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.basic.service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MypageController {
	
	// 생성자 주입
	private final UserService userService;
	
	@GetMapping("/mypage")
	public String mypageForm() {
		return "myPage";
	}
	
	@GetMapping("/mypage/update")
	public String mypageUpdateForm() {
		return "myPageUpdate";
	}
	
	
	
	
	
	
	
	
	
	
//	@PostMapping("/mypage/update")
//	public String update(@ModelAttribute UserDTO userDTO) {
//		userService.update(userDTO);
//		
//		return "redirect:/mypage";
//	}
	
//	@GetMapping("/mypage")
//	public String mypageForm(HttpSession session, Model model) {
//	    String loginEmail = (String) session.getAttribute("loginEmail");  // 세션에서 로그인한 유저의 이메일 가져오기
//	    if (loginEmail == null) {
//	        return "redirect:/login";  // 로그인 안 되어 있으면 로그인 페이지로 리디렉션
//	    }
//
//	    UserDTO userDTO = userService.findByUserEmail(loginEmail);
//	    model.addAttribute("user", userDTO);  // 유저 정보 모델에 추가
//
//	    return "mypage";  // mypage.html로 이동
//	}

	
}
