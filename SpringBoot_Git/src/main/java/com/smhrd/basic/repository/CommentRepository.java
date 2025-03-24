package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
