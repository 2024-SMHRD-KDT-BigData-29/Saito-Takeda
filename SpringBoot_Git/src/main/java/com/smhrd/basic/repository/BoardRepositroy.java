package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.Board;


public interface BoardRepositroy extends JpaRepository<Board, Long>{

}
