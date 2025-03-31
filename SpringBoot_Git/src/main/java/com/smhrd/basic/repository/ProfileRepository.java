package com.smhrd.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, String>{

	ProfileEntity findByUserEmail(String userEmail);

	List<ProfileEntity> findByPartnerMbti(String userMbti);



	
	
	
}
