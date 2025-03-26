package com.smhrd.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 폼
    @GetMapping("/user/save")
    public String saveForm() {
        return "userSave";
    }

    // 회원가입 처리
    @PostMapping("/user/save")
    public String save(@ModelAttribute UserDTO userDTO) {
        userService.save(userDTO);
        return "index";
    }

    // 로그인 폼
    @GetMapping("/user/login")
    public String loginForm() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session, Model model) {
        UserDTO loginResult = userService.login(userDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            return "main";
        } else {
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }

    // 전체 유저 조회
    @GetMapping("/user")
    public String findAll(Model model) {
        List<UserDTO> userDTOList = userService.findAll();
        model.addAttribute("userList", userDTOList);
        return "list";
    }
}