package com.smhrd.basic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.entity.FavoriteEntity;
import com.smhrd.basic.repository.FavoriteRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    // 찜 추가
    public void addFavorite(String userEmail, Integer bidx) {
        // 이미 찜한 게시글인지 확인
        Optional<FavoriteEntity> existingFavorite = favoriteRepository.findByUserEmailAndBidx(userEmail, bidx);
        if (existingFavorite.isEmpty()) {
            // 찜이 없으면 새로 추가
            FavoriteEntity favorite = new FavoriteEntity();
            favorite.setUserEmail(userEmail);
            favorite.setBidx(bidx);
            favoriteRepository.save(favorite);
        }
    }

    // 찜 삭제
    public void removeFavorite(String userEmail, Integer bidx) {
        Optional<FavoriteEntity> existingFavorite = favoriteRepository.findByUserEmailAndBidx(userEmail, bidx);
        existingFavorite.ifPresent(favoriteRepository::delete);
    }

    // 특정 사용자가 특정 게시글을 찜했는지 확인
    public boolean isFavorite(String userEmail, Integer bidx) {
        return favoriteRepository.findByUserEmailAndBidx(userEmail, bidx).isPresent();
    }
}