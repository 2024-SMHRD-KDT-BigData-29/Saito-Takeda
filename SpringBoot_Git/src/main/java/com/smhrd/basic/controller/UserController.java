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
    
    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("로그인 세션 없음, 리다이렉트 to /user/login");
            return "redirect:/user/login";
        }
        if (profileService.findByUserEmail(loginEmail) == null) {
            System.out.println("프로필 없음, 리다이렉트 to /mypage/myprofileEdit");
            return "redirect:/mypage/myprofileEdit";
        }

        // 로그인 유저의 userMbti 가져오기
        ProfileDTO loginProfile = profileService.findByUserEmail(loginEmail);
        String loginUserMbti = loginProfile != null ? loginProfile.getUserMbti() : null;
        System.out.println("로그인 유저 userMbti: " + loginUserMbti);
        model.addAttribute("loginUserMbti", loginUserMbti);

        // 룸메찾기와 방찾기 게시글 가져오기
        List<BoardDTO> roomBoards = boardService.findByBtype("room");
        List<BoardDTO> mateBoards = boardService.findByBtype("mate");
        System.out.println("roomBoards 크기: " + roomBoards.size());
        System.out.println("mateBoards 크기: " + mateBoards.size());

        // 게시글 작성자의 partnerMbti 매핑
        Map<String, String> boardWriterPartnerMbti = new HashMap<>();
        for (BoardDTO board : roomBoards) {
            ProfileDTO writerProfile = profileService.findByUserEmail(board.getBwriter());
            String writerPartnerMbti = writerProfile != null ? writerProfile.getPartnerMbti() : null;
            boardWriterPartnerMbti.put(board.getBwriter(), writerPartnerMbti);
            System.out.println("room board writer: " + board.getBwriter() + ", PartnerMbti: " + writerPartnerMbti);
        }
        for (BoardDTO board : mateBoards) {
            ProfileDTO writerProfile = profileService.findByUserEmail(board.getBwriter());
            String writerPartnerMbti = writerProfile != null ? writerProfile.getPartnerMbti() : null;
            boardWriterPartnerMbti.put(board.getBwriter(), writerPartnerMbti);
            System.out.println("mate board writer: " + board.getBwriter() + ", PartnerMbti: " + writerPartnerMbti);
        }

        // 모델에 데이터 추가
        model.addAttribute("roomBoards", roomBoards);
        model.addAttribute("mateBoards", mateBoards);
        model.addAttribute("boardWriterPartnerMbti", boardWriterPartnerMbti); // 이 부분이 중요!
        UserDTO user = userService.findByUserEmail(loginEmail);
        model.addAttribute("user", user);
        System.out.println("모델에 데이터 추가 완료, boardWriterPartnerMbti: " + boardWriterPartnerMbti);

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
        return "redirect:/user/login";
    }
    
    
}