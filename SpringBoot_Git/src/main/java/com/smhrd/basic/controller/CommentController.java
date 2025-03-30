package com.smhrd.basic.controller;

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

import com.smhrd.basic.dto.CommentDTO;
import com.smhrd.basic.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    
    
    // 댓글 작성
    @PostMapping("/create/{bidx}")
    public String createComment(@PathVariable("bidx") int bidx, 
                                @RequestParam("cmtContent") String cmtContent, 
                                HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/login";
        }

        // 새로운 CommentDTO 생성
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBidx(bidx); // PathVariable에서 받은 bidx 설정
        commentDTO.setCmtContent(cmtContent); // 폼에서 받은 댓글 내용 설정
        commentDTO.setUserEmail(loginEmail); // 로그인한 사용자 이메일 설정

        commentService.createComment(commentDTO);

        return "redirect:/board/detail/" + bidx;
    }

    // 게시글 댓글 조회 (필요 시 사용, 현재는 BoardController에서 처리 중일 가능성 있음)
    @GetMapping("/board/{bidx}")
    public String getComments(@PathVariable("bidx") int bidx, Model model) {
        List<CommentDTO> comments = commentService.getCommentsByBoardId(bidx);
        model.addAttribute("comments", comments);
        return "board/detail"; // 댓글이 포함된 게시글 상세 페이지로 이동 (필요 시 조정)
    }

    // 댓글 삭제
    @PostMapping("/delete/{cmtIdx}")
    public String deleteComment(@PathVariable("cmtIdx") int cmtIdx, 
                                HttpSession session, 
                                @ModelAttribute("bidx") int bidx) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        }

        commentService.deleteComment(cmtIdx, loginEmail);

        // 삭제 후 게시글 상세 페이지로 리다이렉트
        return "redirect:/board/detail/" + bidx;
    }
}