package com.smhrd.basic.controller;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/board") // Boards → Board로 수정
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) throws IOException {
        BoardDTO savedBoard = boardService.createBoardWithFile(boardDTO);
        return ResponseEntity.ok(savedBoard);
    }

    // 게시글 조회 (단일)
    @GetMapping("/{bidx}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable int bidx) {
        BoardDTO board = boardService.getBoard(bidx);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(board);
    }

    // 게시글 목록 조회 (유형별)
    @GetMapping("/type/{btype}")
    public ResponseEntity<List<BoardDTO>> getBoardsByType(@PathVariable String btype) {
        List<BoardDTO> boards = boardService.getBoardsByType(btype);
        return ResponseEntity.ok(boards);
    }

    // 게시글 수정
    @PutMapping("/{bidx}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable int bidx, @RequestBody BoardDTO boardDTO,
                                                @RequestParam String userEmail) throws IOException {
        BoardDTO updatedBoard = boardService.updateBoard(bidx, boardDTO, userEmail);
        return ResponseEntity.ok(updatedBoard);
    }

    // 게시글 삭제
    @DeleteMapping("/{bidx}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int bidx, @RequestParam String userEmail) {
        boardService.deleteBoard(bidx, userEmail);
        return ResponseEntity.noContent().build();
    }

    // 게시글 찜하기/취소
    @PostMapping("/{bidx}/favorite")
    public ResponseEntity<Boolean> toggleFavorite(@PathVariable int bidx, @RequestParam String userEmail) {
        boolean isFavorited = boardService.toggleFavorite(bidx, userEmail);
        return ResponseEntity.ok(isFavorited);
    }
}