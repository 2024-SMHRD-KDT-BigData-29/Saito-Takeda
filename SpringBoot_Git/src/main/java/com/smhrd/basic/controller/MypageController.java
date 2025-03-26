package com.smhrd.basic.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.BoardService;
import com.smhrd.basic.service.ProfileService;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class MypageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private BoardService boardService;

 // 마이페이지 (유저 정보 + 프로필 + 찜한 게시글까지)
    @GetMapping("/mypage")
    public String mypageForm(HttpSession session, Model model) {
    	System.out.println("실행되니?");
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        ProfileDTO profileDTO = profileService.findByUserEmail(loginEmail);
        List<BoardDTO> favoriteBoards = boardService.findFavoriteBoards(loginEmail);

        model.addAttribute("user", userDTO);
        model.addAttribute("profile", profileDTO != null ? profileDTO : new ProfileDTO(loginEmail, null, null, null, null));
        model.addAttribute("favoriteBoards", favoriteBoards);
        return "mypage";
    }

    // 유저 정보 수정 폼
    @GetMapping("/mypage/update")
    public String mypageUpdateForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        model.addAttribute("user", userDTO);
        return "mypageUpdate";
    }

    // 유저 정보 수정 처리
    @PostMapping("/mypage/update")
    public String updateUser(@ModelAttribute UserDTO userDTO, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        userDTO.setUserEmail(loginEmail); // 이메일은 변경 불가
        userService.save(userDTO);
        return "redirect:/mypage";
    }

    // 프로필 수정 폼
    @GetMapping("/mypage/profile")
    public String profileUpdateForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        ProfileDTO profileDTO = profileService.findByUserEmail(loginEmail);
        model.addAttribute("profile", profileDTO != null ? profileDTO : new ProfileDTO(loginEmail, null, null, null, null));
        return "profileUpdate";
    }
    
    // 프로필 수정 처리 (파일 업로드 포함)
    @PostMapping("/mypage/profile")
    public String updateProfile(@ModelAttribute ProfileDTO profileDTO, HttpSession session) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        profileDTO.setUserEmail(loginEmail);
        profileService.save(profileDTO);
        return "redirect:/mypage";
    }

    
}
