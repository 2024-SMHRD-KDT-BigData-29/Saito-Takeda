package com.smhrd.basic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	
	// 글 유형으로 게시글 조회
    List<BoardEntity> findByBidxIn(List<Integer> bidxList);
	
	// 작성자로 게시글 조회
    List<BoardEntity> findByBwriter(String bwriter);

    // 글 유형으로 게시글 조회
    List<BoardEntity> findByBtype(String bType);

	List<BoardEntity> findByBtypeAndBwriterIn(String btype, List<String> writerEmails);

    
    //
	
}
