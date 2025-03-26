package com.smhrd.basic.controller;


import java.io.IOException;
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
import com.smhrd.basic.service.BoardService;

// 트러블슈팅 bIdx 변수명 <- JPA에 안맞아서 소문자로 다른컬럼들도 bidx 와 같이 다 소문자로 변경


@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 (HTML)
    @GetMapping
    public String getBoardList(@RequestParam(defaultValue = "room") String btype, Model model) {
        List<BoardDTO> boards = boardService.getBoardsByType(btype);
        model.addAttribute("board", boards);
        return "board/list"; // templates/board/list.html
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
    public String createBoardForm(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/write"; // templates/board/write.html
    }

    // 게시글 작성 처리
    @PostMapping
    public String createBoard(@ModelAttribute BoardDTO boardDTO) throws IOException {
    	// 파일 업로드 포함 처리예정
//    	boardService.createBoardWithFile(boardDTO);
        boardService.createBoard(boardDTO);
        return "redirect:/board"; // 작성 후 목록으로 리다이렉트
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
}