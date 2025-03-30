package com.smhrd.basic.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.dto.CommentDTO;
import com.smhrd.basic.entity.CommentEntity;
import com.smhrd.basic.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 작성
    public CommentDTO createComment(CommentDTO commentDTO) {
        // CommentDTO를 CommentEntity로 변환
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBidx(commentDTO.getBidx()); // 게시글 ID 설정
        commentEntity.setCmtContent(commentDTO.getCmtContent()); // 댓글 내용 설정
        commentEntity.setUserEmail(commentDTO.getUserEmail()); // 작성자 이메일 설정
        commentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis())); // 작성일 설정

        // 데이터베이스에 저장
        CommentEntity savedEntity = commentRepository.save(commentEntity);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return new CommentDTO(
            savedEntity.getCmtIdx(),
            savedEntity.getBidx(),
            savedEntity.getCmtContent(),
            savedEntity.getCreatedAt(),
            savedEntity.getUserEmail()
        );
    }

    // 게시글 ID로 댓글 조회
    public List<CommentDTO> getCommentsByBoardId(int bidx) {
        List<CommentEntity> entities = commentRepository.findByBidx(bidx);
        return entities.stream()
            .map(entity -> new CommentDTO(
                entity.getCmtIdx(),
                entity.getBidx(),
                entity.getCmtContent(),
                entity.getCreatedAt(),
                entity.getUserEmail()
            ))
            .toList();
    }

    // 댓글 삭제
    public void deleteComment(int cmtIdx, String loginEmail) {
        CommentEntity comment = commentRepository.findById(cmtIdx)
            .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        if (!comment.getUserEmail().equals(loginEmail)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        commentRepository.deleteById(cmtIdx);
    }
}