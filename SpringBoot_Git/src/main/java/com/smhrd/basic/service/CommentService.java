package com.smhrd.basic.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.CommentDTO;
import com.smhrd.basic.entity.BoardEntity;
import com.smhrd.basic.entity.CommentEntity;
import com.smhrd.basic.repository.BoardRepository;
import com.smhrd.basic.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    // 댓글 작성
    @Transactional
    public CommentDTO createComment(CommentDTO commentDTO) {
        // 1. 게시글 존재 여부 확인
        BoardEntity board = boardRepository.findById(commentDTO.getBidx())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + commentDTO.getBidx()));

        // 2. DTO -> Entity 변환
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBidx(commentDTO.getBidx());
        commentEntity.setCmtContent(commentDTO.getCmtContent());
        commentEntity.setUserEmail(commentDTO.getUserEmail());
        commentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis())); // 현재 시간 설정

        // 3. 저장
        CommentEntity savedEntity = commentRepository.save(commentEntity);

        // 4. Entity -> DTO 변환 후 반환
        return entityToDto(savedEntity);
    }

    // 특정 게시글의 댓글 목록 조회
    public List<CommentDTO> getCommentsByBoardId(int bIdx) {
        List<CommentEntity> comments = commentRepository.findByBidx(bIdx);
        return comments.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(int cmtIdx, String userEmail) {
        CommentEntity comment = commentRepository.findById(cmtIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다: " + cmtIdx));

        // 작성자 본인만 삭제 가능하도록 체크
        if (!comment.getUserEmail().equals(userEmail)) {
            throw new SecurityException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

    // Entity -> DTO 변환 메서드
    private CommentDTO entityToDto(CommentEntity entity) {
        return new CommentDTO(
                entity.getCmtIdx(),
                entity.getBidx(),
                entity.getCmtContent(),
                entity.getCreatedAt(),
                entity.getUserEmail()
        );
    }
    //
}
