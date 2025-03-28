package com.smhrd.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    // 특정 게시글(bIdx)에 달린 모든 댓글 조회
    List<CommentEntity> findByBidx(int bIdx);

    // 특정 사용자가 작성한 모든 댓글 조회
    List<CommentEntity> findByUserEmail(String userEmail);

    // 특정 게시글의 댓글 수 조회 (필요 시 사용)
    long countByBidx(int bIdx);
    
    //
}
