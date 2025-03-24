package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.basic.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
