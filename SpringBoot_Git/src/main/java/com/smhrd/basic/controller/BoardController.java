package com.smhrd.basic.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.BoardService;
import com.smhrd.basic.service.CommentService;
import com.smhrd.basic.service.FavoriteService;
import com.smhrd.basic.service.ProfileService;
import com.smhrd.basic.service.UserService;

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
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;

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
    public String listBoards(Model model, HttpSession session, @RequestParam(value = "type", defaultValue = "all") String type) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        if (profileService.findByUserEmail(loginEmail) == null) {
            return "redirect:/mypage/myprofileEdit";
        }

        List<BoardDTO> allBoards = boardService.findAll();
        List<BoardDTO> roomBoards = boardService.findByBtype("room");
        List<BoardDTO> mateBoards = boardService.findByBtype("mate");

        model.addAttribute("allBoards", allBoards);
        model.addAttribute("roomBoards", roomBoards);
        model.addAttribute("mateBoards", mateBoards);
        model.addAttribute("allCount", allBoards.size());
        model.addAttribute("roomCount", roomBoards.size());
        model.addAttribute("mateCount", mateBoards.size());

        model.addAttribute("currentType", type);

        List<BoardDTO> displayBoards;
        String displayTitle;
        switch (type) {
            case "room":
                displayBoards = roomBoards;
                displayTitle = "룸메 찾기";
                break;
            case "mate":
                displayBoards = mateBoards;
                displayTitle = "방 찾기";
                break;
            case "all":
            default:
                displayBoards = allBoards;
                displayTitle = "전체";
                break;
        }
        model.addAttribute("displayBoards", displayBoards);
        model.addAttribute("displayTitle", displayTitle);

     // 현재 로그인한 사용자의 userRole 추가
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        String userRole = (userDTO != null) ? userDTO.getUserRole() : "u"; // 기본값으로 "u" 설정
        model.addAttribute("userRole", userRole);
        
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
        model.addAttribute("comments", commentService.getCommentsByBoardId(bidx));
        model.addAttribute("profileDTO", profileService.findByUserEmail(boardDTO.getBwriter()));
        return "board/detail";
    }
    
 // 게시글 삭제 (관리자용)
    @PostMapping("/admin/delete/{bidx}")
    public String adminDeleteBoard(@PathVariable("bidx") int bidx, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        if (userDTO == null || !"a".equals(userDTO.getUserRole())) {
            return "redirect:/board/list"; // 관리자가 아니면 목록으로 리다이렉트
        }
        // userRole 전달
        boardService.deleteBoard(bidx, loginEmail, userDTO.getUserRole());
        return "redirect:/board/list";
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
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        String userRole = (userDTO != null) ? userDTO.getUserRole() : "u";
        boardService.deleteBoard(bidx, loginEmail, userRole);
        return "redirect:/board/list";
    }

    
    
    // 찜한 게시글 처리
    @PostMapping("/favorite/{bidx}")
    public String toggleFavorite(@PathVariable("bidx") int bidx, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        favoriteService.toggleFavorite(loginEmail, bidx);
        return "redirect:/board/detail/" + bidx;
    }
    
    
}