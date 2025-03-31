package com.smhrd.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.BoardService;
import com.smhrd.basic.service.ProfileService;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String loginForm() {
        return "user/login";
    }
    
 // 로그인 세션 확인 후 이동
    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }

        // 사용자 정보 조회
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
            System.out.println("사용자 정보 추가: userNickname = " + userDTO.getUserNickname());
        } else {
            System.out.println("사용자 정보 조회 실패: " + loginEmail);
        }
        //
     // 모든 게시글 가져오기
        List<BoardDTO> roomBoards = boardService.findByBtype("room");
        List<BoardDTO> mateBoards = boardService.findByBtype("mate");
        //
     // 게시글 작성자의 userMbti 맵핑
        Map<String, String> boardWriterMbti = new HashMap<>();
        for (BoardDTO board : roomBoards) {
            ProfileDTO writerProfile = profileService.findByUserEmail(board.getBwriter());
            boardWriterMbti.put(board.getBwriter(), writerProfile != null ? writerProfile.getUserMbti() : null);
        }
        for (BoardDTO board : mateBoards) {
            ProfileDTO writerProfile = profileService.findByUserEmail(board.getBwriter());
            boardWriterMbti.put(board.getBwriter(), writerProfile != null ? writerProfile.getUserMbti() : null);
        }

        // 게시글 목록 조회
        model.addAttribute("roomBoards", boardService.findByBtype("room"));
        model.addAttribute("mateBoards", boardService.findByBtype("mate"));
        model.addAttribute("boardWriterMbti", boardWriterMbti);
        model.addAttribute("user", userService.findByUserEmail(loginEmail)); // userNickname용
        

        return "main";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam("userEmail") String userEmail, 
                        @RequestParam("userPw") String userPw, 
                        HttpSession session, 
                        Model model) {
        System.out.println("로그인 시도: " + userEmail);
        UserDTO userDTO = userService.findByUserEmail(userEmail);
        if (userDTO != null && userDTO.getUserPw().equals(userPw)) {
            System.out.println("사용자 인증 성공: " + userEmail);
            session.setAttribute("loginEmail", userEmail);
            System.out.println("세션 저장: loginEmail = " + session.getAttribute("loginEmail"));

            // 프로필 조회
            ProfileDTO profileDTO = profileService.findByUserEmail(userEmail);
            if (profileDTO == null || profileDTO.getUserMbti() == null || profileDTO.getUserMbti().isEmpty()) {
                System.out.println("프로필 데이터 없음 또는 MBTI 미설정: " + userEmail + " -> profileEdit.html로 이동");
                return "redirect:/mypage/update";
            } else {
                System.out.println("프로필 데이터 및 MBTI 설정됨: " + userEmail + " -> main.html로 이동");
                return "redirect:/main";
            }
        } else {
            System.out.println("사용자 인증 실패: " + userEmail);
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "index";
        }
    }

    
    @GetMapping("/user/join")
    public String joinForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/join"; 
    }

    @PostMapping("/user/join") 
    public String join(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            // 이메일 중복 확인
            if (userService.findByUserEmail(userDTO.getUserEmail()) != null) {
                model.addAttribute("error", "이미 사용 중인 이메일입니다.");
                return "join";
            }

            // 회원가입 처리
            userService.save(userDTO);
            return "redirect:/user/login";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return "join";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    
}