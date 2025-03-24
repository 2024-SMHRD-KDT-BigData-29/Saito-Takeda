package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.BoardEntity;


public interface BoardRepositroy extends JpaRepository<BoardEntity, Long>{

}
