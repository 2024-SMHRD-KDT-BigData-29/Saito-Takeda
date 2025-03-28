package com.smhrd.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.BoardService;
import com.smhrd.basic.service.FavoriteService;
import com.smhrd.basic.service.MypageService;
import com.smhrd.basic.service.ProfileService;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private BoardService boardService;
    
    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private MypageService mypageService;

 // 마이페이지 메인 (유저 정보 + 프로필)
    @GetMapping("/index")
    public String mypage(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }

        // 프로필 정보 조회
        ProfileDTO profileDTO = mypageService.findProfileByUserEmail(loginEmail);
        if (profileDTO == null) {
            profileDTO = new ProfileDTO();
            profileDTO.setUserEmail(loginEmail);
        }
        model.addAttribute("profileDTO", profileDTO);

        // 유저 정보 조회
        UserDTO userDTO = mypageService.findUserByEmail(loginEmail);
        model.addAttribute("userDTO", userDTO);

        return "mypage/index";
    }
    
 // 찜한 게시글 보기 페이지
    @GetMapping("/favoriteBoard")
    public String favoriteBoard(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        List<BoardDTO> favoriteBoards = boardService.findFavoriteBoards(loginEmail);
        model.addAttribute("favoriteBoards", favoriteBoards);
        return "mypage/favoriteBoard";
    }
    
 // 유저 정보 수정 폼
    @GetMapping("/userEdit")
    public String userEditForm(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        UserDTO userDTO = mypageService.findUserByEmail(loginEmail);
        model.addAttribute("userDTO", userDTO);
        return "mypage/userEdit";
    }
    
 // 유저 정보 수정 처리
    @PostMapping("/userEdit")
    public String updateUser(@ModelAttribute UserDTO userDTO, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        userDTO.setUserEmail(loginEmail); // userEmail은 변경 불가
        mypageService.updateUser(userDTO);
        return "redirect:/mypage/index";
    }

    // 신규 회원 프로필 입력 페이지
    @GetMapping("/profileEdit")
    public String myprofileEdit(Model model, HttpSession session, @RequestParam(value = "userEmail", required = false) String userEmail) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null && userEmail == null) {
            System.out.println("세션 및 userEmail 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        // 세션의 loginEmail이 있으면 우선 사용, 없으면 쿼리 파라미터의 userEmail 사용
        String emailToUse = (loginEmail != null) ? loginEmail : userEmail;
        System.out.println("프로필 입력 페이지 요청: userEmail = " + emailToUse);

        // 기존 프로필 데이터 조회
        ProfileDTO profileDTO = profileService.findByUserEmail(emailToUse);
        if (profileDTO == null) {
            profileDTO = new ProfileDTO();
            profileDTO.setUserEmail(emailToUse);
        }
        model.addAttribute("profileDTO", profileDTO);
        return "mypage/profileEdit";
    }

    // 신규 회원 프로필 저장 처리
    @PostMapping("/mypage/profileEdit")
    public String saveProfile(@ModelAttribute ProfileDTO profileDTO, 
                              @RequestParam(value = "ei", required = false) String ei,
                              @RequestParam(value = "sn", required = false) String sn,
                              @RequestParam(value = "ft", required = false) String ft,
                              @RequestParam(value = "jp", required = false) String jp,
                              HttpSession session,
                              Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }

        System.out.println("프로필 저장 요청: userEmail = " + loginEmail);
        // MBTI 조합
        if (ei != null && sn != null && ft != null && jp != null) {
            String userMbti = ei + sn + ft + jp;
            profileDTO.setUserMbti(userMbti);
        }

        // 프로필 사진 업로드 처리
        if (profileDTO.getProfileFile() != null && !profileDTO.getProfileFile().isEmpty()) {
            String contentType = profileDTO.getProfileFile().getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                System.out.println("이미지 파일 오류: " + profileDTO.getProfileFile().getOriginalFilename());
                model.addAttribute("error", "이미지 파일만 업로드 가능합니다.");
                model.addAttribute("profileDTO", profileDTO);
                return "mypage/profileEdit";
            }
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "_" + profileDTO.getProfileFile().getOriginalFilename();
            File destFile = new File(uploadDir + fileName);
            profileDTO.getProfileFile().transferTo(destFile);
            profileDTO.setProfileImg("/uploads/" + fileName);
        }

        // 프로필 저장
        profileDTO.setUserEmail(loginEmail);
        profileService.save(profileDTO);
        System.out.println("프로필 저장 성공: userEmail = " + loginEmail);
        return "redirect:/main";
    }

 // 기존 회원 프로필 수정 폼
    @GetMapping("/profile")
    public String profileUpdateForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        System.out.println("프로필 수정 폼 요청: userEmail = " + loginEmail);
        ProfileDTO profileDTO = profileService.findByUserEmail(loginEmail);
        if (profileDTO == null) {
            profileDTO = new ProfileDTO();
            profileDTO.setUserEmail(loginEmail);
        }
        model.addAttribute("profileDTO", profileDTO);
        return "mypage/profileUpdate";
    }
    
    
    
 // 기존 회원 프로필 수정 처리
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute ProfileDTO profileDTO, 
                                @RequestParam(value = "ei", required = false) String ei,
                                @RequestParam(value = "sn", required = false) String sn,
                                @RequestParam(value = "ft", required = false) String ft,
                                @RequestParam(value = "jp", required = false) String jp,
                                HttpSession session,
                                Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            System.out.println("세션 없음 -> 로그인 페이지로 리다이렉트");
            return "redirect:/user/login";
        }
        System.out.println("프로필 업데이트 요청: userEmail = " + loginEmail);

        if (ei != null && sn != null && ft != null && jp != null) {
            String userMbti = ei + sn + ft + jp;
            profileDTO.setUserMbti(userMbti);
        }

        if (profileDTO.getProfileFile() != null && !profileDTO.getProfileFile().isEmpty()) {
            String contentType = profileDTO.getProfileFile().getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                System.out.println("이미지 파일 오류: " + profileDTO.getProfileFile().getOriginalFilename());
                model.addAttribute("error", "이미지 파일만 업로드 가능합니다.");
                model.addAttribute("profileDTO", profileDTO);
                return "mypage/profileUpdate";
            }
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "_" + profileDTO.getProfileFile().getOriginalFilename();
            File destFile = new File(uploadDir + fileName);
            profileDTO.getProfileFile().transferTo(destFile);
            profileDTO.setProfileImg("/uploads/" + fileName);
        }

        profileDTO.setUserEmail(loginEmail);
        profileService.save(profileDTO);
        System.out.println("프로필 업데이트 성공: userEmail = " + loginEmail);
        return "redirect:/mypage/index";
    }
}