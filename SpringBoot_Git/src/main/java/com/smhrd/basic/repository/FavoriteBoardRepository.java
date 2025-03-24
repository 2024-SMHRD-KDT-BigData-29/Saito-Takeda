package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.FavoriteBoardEntity;

public interface FavoriteBoardRepository extends JpaRepository<FavoriteBoardEntity, Long>{

}
