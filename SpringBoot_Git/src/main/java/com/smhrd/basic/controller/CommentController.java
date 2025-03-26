package com.smhrd.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.basic.dto.CommentDTO;
import com.smhrd.basic.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.createComment(commentDTO);
        return ResponseEntity.ok(savedComment);
    }

    // 게시글 댓글 조회
    @GetMapping("/board/{bidx}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable int bIdx) {
        List<CommentDTO> comments = commentService.getCommentsByBoardId(bIdx);
        return ResponseEntity.ok(comments);
    }

    // 댓글 삭제
    @DeleteMapping("/{cmtIdx}")
    public ResponseEntity<Void> deleteComment(@PathVariable int cmtIdx, @RequestParam String userEmail) {
        commentService.deleteComment(cmtIdx, userEmail);
        return ResponseEntity.noContent().build();
    }
}
