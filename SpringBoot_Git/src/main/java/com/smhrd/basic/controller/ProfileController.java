package com.smhrd.basic.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.service.ProfileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/edit")
    public String editProfileForm(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        ProfileDTO profileDTO = profileService.findByUserEmail(loginEmail);
        if (profileDTO == null) {
            profileDTO = new ProfileDTO();
            profileDTO.setUserEmail(loginEmail);
        }
        System.out.println("ProfileDTO in model: " + profileDTO); // 디버깅 로그
        model.addAttribute("profileDTO", profileDTO);
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute ProfileDTO profileDTO,
                              @RequestParam("ei") String ei,
                              @RequestParam("sn") String sn,
                              @RequestParam("ft") String ft,
                              @RequestParam("jp") String jp,
                              HttpSession session,
                              Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        profileDTO.setUserEmail(loginEmail);
        profileDTO.setEi(ei);
        profileDTO.setSn(sn);
        profileDTO.setFt(ft);
        profileDTO.setJp(jp);

        // 파일 업로드 처리
        try {
            if (profileDTO.getProfileFile() != null && !profileDTO.getProfileFile().isEmpty()) {
                // 파일 형식 검증 (이미지 파일만 허용)
                String contentType = profileDTO.getProfileFile().getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    model.addAttribute("error", "이미지 파일만 업로드 가능합니다.");
                    model.addAttribute("profileDTO", profileDTO);
                    return "profile/edit";
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
        } catch (IOException e) {
            model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("profileDTO", profileDTO);
            return "profile/edit";
        }

        profileService.save(profileDTO);
        return "redirect:/board";
    }
    //
}