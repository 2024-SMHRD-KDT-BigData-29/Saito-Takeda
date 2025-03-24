package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.FavoriteBoard;

public interface FavoriteBoardRepository extends JpaRepository<FavoriteBoard, Long>{

}
