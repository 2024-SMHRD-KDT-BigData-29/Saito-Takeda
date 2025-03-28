package com.smhrd.basic.controller;

import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.ProfileService;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService; // ProfileService 추가

    // 초기 화면
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // /user/login (GET) 요청도 index.html로 리다이렉트
    @GetMapping("/user/login")
    public String loginForm() {
        return "index";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
        UserDTO loginResult = userService.login(userDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            // 프로필 정보 확인
            ProfileDTO profileDTO = profileService.findByUserEmail(loginResult.getUserEmail());
            if (profileDTO == null) {
                // 프로필 정보가 없으면 프로필 입력 페이지로 리다이렉트
                return "redirect:/profile/edit";
            } else {
                // 프로필 정보가 있으면 메인 페이지로 리다이렉트
                return "redirect:/board";
            }
        } else {
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "index";
        }
    }

    // 회원가입 폼
    @GetMapping("/user/join")
    public String joinForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "join";
    }

    // 회원가입 처리
    @PostMapping("/user/join")
    public String save(@ModelAttribute UserDTO userDTO) {
        userService.save(userDTO);
        return "index";
    }

    // 로그아웃 처리
    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}