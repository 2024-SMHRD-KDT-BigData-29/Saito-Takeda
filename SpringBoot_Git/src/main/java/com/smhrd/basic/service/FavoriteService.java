package com.smhrd.basic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.entity.FavoriteEntity;
import com.smhrd.basic.repository.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    // 찜하기 추가 및 삭제 통합ver
    @Transactional
    public void toggleFavorite(String userEmail, int bidx) {
        Optional<FavoriteEntity> existingFavorite = favoriteRepository.findByUserEmailAndBidx(userEmail, bidx);
        if (existingFavorite.isPresent()) {
            favoriteRepository.delete(existingFavorite.get());
        } else {
            FavoriteEntity favorite = new FavoriteEntity(null, userEmail, bidx);
            favoriteRepository.save(favorite);
        }
    }

    // 특정 사용자가 특정 게시글을 찜했는지 확인
    public boolean isFavorite(String userEmail, Integer bidx) {
        return favoriteRepository.findByUserEmailAndBidx(userEmail, bidx).isPresent();
    }
    
    
}