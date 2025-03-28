package com.smhrd.basic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.FavoriteEntity;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {

    // 특정 사용자가 특정 게시글을 찜했는지 확인
    Optional<FavoriteEntity> findByBidxAndUserEmail(int bIdx, String userEmail);

    // 사용자가 찜한 게시글 목록 조회
    List<FavoriteEntity> findByUserEmail(String userEmail);
    
    // 테이블 삭제 이메일이랑 보드 인덱스 조건식
    void deleteByUserEmailAndBidx(String userEmail, Integer bidx);

	Optional<FavoriteEntity> findByUserEmailAndBidx(String userEmail, int bidx);
}
