package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.entity.ProfileEntity;


public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {

    ProfileEntity findByUserEmail(String userEmail);
}
