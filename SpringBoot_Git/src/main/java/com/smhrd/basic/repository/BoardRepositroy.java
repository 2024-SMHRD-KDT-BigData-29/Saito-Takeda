package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.Board;

@Repository
public interface BoardRepositroy extends JpaRepository<Board, Integer>{

}
