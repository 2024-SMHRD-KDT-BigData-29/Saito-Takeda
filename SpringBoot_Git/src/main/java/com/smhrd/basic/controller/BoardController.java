package com.smhrd.basic.controller;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.service.BoardService;
import com.smhrd.basic.service.ProfileService;
import com.smhrd.basic.service.UserService;

import jakarta.servlet.http.HttpSession;

// 트러블슈팅 bIdx 변수명 <- JPA에 안맞아서 소문자로 다른컬럼들도 bidx 와 같이 다 소문자로 변경


@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProfileService profileService;

    // 유저로그인세션 확인 및 프로필 정보입력 여부 확인
    @GetMapping
    public String listBoards(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        System.out.println("BoardController - loginEmail: " + loginEmail);
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        System.out.println("BoardController - userDTO: " + userDTO);
        if (userDTO == null) {
            session.invalidate();
            return "redirect:/user/login";
        }

        // 프로필 정보 입력 여부 확인
        ProfileDTO profileDTO = profileService.findByUserEmail(loginEmail);
        if (profileDTO == null) {
            // 프로필 정보가 없으면 프로필 입력 페이지로 리다이렉트
            return "redirect:/profile/edit";
        }

        List<BoardDTO> allBoards = boardService.findAll();
        List<BoardDTO> roomBoards = allBoards.stream()
                .filter(board -> "room".equals(board.getBtype()))
                .collect(Collectors.toList());
        List<BoardDTO> mateBoards = allBoards.stream()
                .filter(board -> "mate".equals(board.getBtype()))
                .collect(Collectors.toList());

        model.addAttribute("user", userDTO);
        model.addAttribute("profile", profileDTO);
        model.addAttribute("roomBoards", roomBoards);
        model.addAttribute("mateBoards", mateBoards);
        return "main";
    }

    // 게시글 상세 페이지 (HTML)
    @GetMapping("/{bidx}")
    public String getBoard(@PathVariable int bidx, Model model) {
        BoardDTO board = boardService.getBoard(bidx);
        model.addAttribute("board", board);
        return "board/detail"; // templates/board/detail.html
    }

 // 게시글 작성 폼
    @GetMapping("/new")
    public String createBoardForm(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/";
        }
        // 프로필 정보 입력 여부 확인
        ProfileDTO profileDTO = profileService.findByUserEmail(loginEmail);
        if (profileDTO == null) {
            return "redirect:/profile/edit";
        }
        UserDTO userDTO = userService.findByUserEmail(loginEmail);
        model.addAttribute("user", userDTO);
        model.addAttribute("profile", profileDTO);
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/form";
    }
    
 // 게시글 작성 처리
    @PostMapping
    public String createBoard(@ModelAttribute BoardDTO boardDTO, HttpSession session) throws IOException {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/";
        }
        boardDTO.setBwriter(loginEmail);
        boardService.createBoardWithFile(boardDTO);
        return "redirect:/boards";
    }

    // 게시글 수정 폼
    @GetMapping("/{bidx}/edit")
    public String editBoardForm(@PathVariable int bidx, Model model) {
        BoardDTO board = boardService.getBoard(bidx);
        model.addAttribute("board", board);
        return "board/edit"; // templates/board/edit.html
    }

    // 게시글 수정 처리
    @PostMapping("/{bidx}/edit")
    public String updateBoard(@PathVariable int bidx, @ModelAttribute BoardDTO boardDTO, 
                              @RequestParam String userEmail) throws IOException {
    	// 파일 업로드 포함 처리예정
//    	boardService.updateBoardWithFile(bIdx, boardDTO, userEmail);
        boardService.updateBoard(bidx, boardDTO, userEmail);
        return "redirect:/board/" + bidx; // 수정 후 상세 페이지로 리다이렉트
    }

    // 게시글 삭제
    @PostMapping("/{bidx}/delete")
    public String deleteBoard(@PathVariable int bidx, @RequestParam String userEmail) {
        boardService.deleteBoard(bidx, userEmail);
        return "redirect:/board"; // 삭제 후 목록으로 리다이렉트
    }
    //
}