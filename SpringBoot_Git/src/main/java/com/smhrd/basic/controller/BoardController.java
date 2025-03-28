package com.smhrd.basic.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.service.BoardService;
import com.smhrd.basic.service.FavoriteService;
import com.smhrd.basic.service.ProfileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private FavoriteService favoriteService;

    // 게시글 작성
    @GetMapping("/form")
    public String writeForm(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBwriter(loginEmail);
        model.addAttribute("boardDTO", boardDTO);
        return "board/form";
    }

    // 게시글 작성처리
    @PostMapping("/form")
    public String saveBoard(@ModelAttribute BoardDTO boardDTO, HttpSession session, Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        boardDTO.setBwriter(loginEmail);
        
     // 작성일 설정
        boardDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            // 첨부파일 (방 사진) 업로드 처리
            if (boardDTO.getFile() != null && !boardDTO.getFile().isEmpty()) {
                String contentType = boardDTO.getFile().getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    model.addAttribute("error", "이미지 파일만 업로드 가능합니다.");
                    model.addAttribute("boardDTO", boardDTO);
                    return "board/form";
                }
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String fileName = System.currentTimeMillis() + "_" + boardDTO.getFile().getOriginalFilename();
                File destFile = new File(uploadDir + fileName);
                boardDTO.getFile().transferTo(destFile);
                boardDTO.setBfile("/uploads/" + fileName);
            }

            // 룸메 찾기: 임대차 계약서 업로드 처리 (선택사항, PDF와 이미지 허용)
            if ("room".equals(boardDTO.getBtype()) && boardDTO.getLeaseContract() != null && !boardDTO.getLeaseContract().isEmpty()) {
                String contentType = boardDTO.getLeaseContract().getContentType();
                if (contentType == null || (!contentType.startsWith("application/pdf") && !contentType.startsWith("image/"))) {
                    model.addAttribute("error", "임대차 계약서는 PDF 또는 이미지 파일만 업로드 가능합니다.");
                    model.addAttribute("boardDTO", boardDTO);
                    return "board/form";
                }
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String fileName = System.currentTimeMillis() + "_" + boardDTO.getLeaseContract().getOriginalFilename();
                File destFile = new File(uploadDir + fileName);
                boardDTO.getLeaseContract().transferTo(destFile);
                boardDTO.setLeaseContractPath("/uploads/" + fileName);
            }

            // 방 찾기: 범죄경력회보서 업로드 처리 (선택사항, PDF와 이미지 허용)
            if ("mate".equals(boardDTO.getBtype()) && boardDTO.getCriminalRecord() != null && !boardDTO.getCriminalRecord().isEmpty()) {
                String contentType = boardDTO.getCriminalRecord().getContentType();
                if (contentType == null || (!contentType.startsWith("application/pdf") && !contentType.startsWith("image/"))) {
                    model.addAttribute("error", "범죄경력회보서는 PDF 또는 이미지 파일만 업로드 가능합니다.");
                    model.addAttribute("boardDTO", boardDTO);
                    return "board/form";
                }
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String fileName = System.currentTimeMillis() + "_" + boardDTO.getCriminalRecord().getOriginalFilename();
                File destFile = new File(uploadDir + fileName);
                boardDTO.getCriminalRecord().transferTo(destFile);
                boardDTO.setCriminalRecordPath("/uploads/" + fileName);
            }

            boardService.save(boardDTO);
            return "redirect:/board/list";
        } catch (IOException e) {
            model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("boardDTO", boardDTO);
            return "board/form";
        }
    }

    // 게시글 목록가기
    @GetMapping("/list")
    public String listBoards(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        if (profileService.findByUserEmail(loginEmail) == null) {
            return "redirect:/mypage/myprofileEdit";
        }
        model.addAttribute("roomBoards", boardService.findByBtype("room"));
        model.addAttribute("mateBoards", boardService.findByBtype("mate"));
        return "board/list";
    }

    // 게시글 클릭해서 보기
    @GetMapping("/detail/{bidx}")
    public String boardDetail(@PathVariable("bidx") int bidx, Model model, HttpSession session) {
        BoardDTO boardDTO = boardService.findByBidx(bidx);
        if (boardDTO == null) {
            return "redirect:/board/list";
        }
        String loginEmail = (String) session.getAttribute("loginEmail");
        model.addAttribute("board", boardDTO);
        model.addAttribute("isOwner", loginEmail != null && loginEmail.equals(boardDTO.getBwriter()));
        return "board/detail";
    }

    // 게시글 수정하기
    @GetMapping("/edit/{bidx}")
    public String editForm(@PathVariable("bidx") int bidx, Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        BoardDTO boardDTO = boardService.findByBidx(bidx);
        if (boardDTO == null || !boardDTO.getBwriter().equals(loginEmail)) {
            return "redirect:/board/list";
        }
        model.addAttribute("boardDTO", boardDTO);
        return "board/edit";
    }

    // 게시글 수정처리
    @PostMapping("/edit/{bidx}")
    public String updateBoard(@PathVariable("bidx") int bidx, @ModelAttribute BoardDTO boardDTO, HttpSession session, Model model) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        boardDTO.setBwriter(loginEmail);

        try {
            // 첨부파일 업로드 처리 (bfile)
            if (boardDTO.getFile() != null && !boardDTO.getFile().isEmpty()) {
                String contentType = boardDTO.getFile().getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    model.addAttribute("error", "이미지 파일만 업로드 가능합니다.");
                    model.addAttribute("boardDTO", boardDTO);
                    return "board/edit";
                }
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String fileName = System.currentTimeMillis() + "_" + boardDTO.getFile().getOriginalFilename();
                File destFile = new File(uploadDir + fileName);
                boardDTO.getFile().transferTo(destFile);
                boardDTO.setBfile("/uploads/" + fileName);
            }

            // 방 찾기 유형일 경우 자신의 사진 업로드 처리 (userPhoto)
            if ("mate".equals(boardDTO.getBtype()) && boardDTO.getUserPhotoFile() != null && !boardDTO.getUserPhotoFile().isEmpty()) {
                String contentType = boardDTO.getUserPhotoFile().getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    model.addAttribute("error", "이미지 파일만 업로드 가능합니다.");
                    model.addAttribute("boardDTO", boardDTO);
                    return "board/edit";
                }
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String fileName = System.currentTimeMillis() + "_" + boardDTO.getUserPhotoFile().getOriginalFilename();
                File destFile = new File(uploadDir + fileName);
                boardDTO.getUserPhotoFile().transferTo(destFile);
                boardDTO.setUserPhoto("/uploads/" + fileName);
            }

            boardService.updateBoard(bidx, boardDTO, loginEmail);
            return "redirect:/board/detail/" + bidx;
        } catch (IOException e) {
            model.addAttribute("error", "파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("boardDTO", boardDTO);
            return "board/edit";
        }
    }

    // 게시글 삭제
    @PostMapping("/delete/{bidx}")
    public String deleteBoard(@PathVariable("bidx") int bidx, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        boardService.deleteBoard(bidx, loginEmail);
        return "redirect:/board/list";
    }

    // 찜한 게시글 GetMapping이 없음
    @GetMapping("/favoriteBoard/{favoriteIdx}")
    public String favoriteBoard(@PathVariable("favoriteIdx") Integer favoriteIdx, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }

        // 찜 상태 확인
        boolean isFavorite = favoriteService.isFavorite(loginEmail, favoriteIdx);
        if (isFavorite) {
            // 이미 찜한 상태라면 삭제
            favoriteService.removeFavorite(loginEmail, favoriteIdx);
        } else {
            // 찜하지 않은 상태라면 추가
            favoriteService.addFavorite(loginEmail, favoriteIdx);
        }

        return "redirect:/mypage/index";
    }
    
    
    // 찜한 게시글 처리
    @PostMapping("/favorite/{bidx}")
    public String toggleFavorite(@PathVariable("bidx") int bidx, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        boardService.toggleFavorite(bidx, loginEmail);
        return "redirect:/board/detail" + bidx;
    }
}