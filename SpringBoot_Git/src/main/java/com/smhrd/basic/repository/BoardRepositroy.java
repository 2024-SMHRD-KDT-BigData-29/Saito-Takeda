package com.smhrd.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.BoardEntity;

@Repository
public interface BoardRepositroy extends JpaRepository<BoardEntity, Integer>{
	
	// 글 유형으로 게시글 조회
    List<BoardEntity> findByBidx(int bIdx);
	
	// 작성자로 게시글 조회
    List<BoardEntity> findByUserEmail(String userEmail);

    // 글 유형으로 게시글 조회
    List<BoardEntity> findByBtype(String bType);
	
}
