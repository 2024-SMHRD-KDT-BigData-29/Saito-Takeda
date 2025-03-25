package com.smhrd.basic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	// 기본적으로 제공하는 CRUD
		// 1. findAll()
		// - select * from 테이블명;
		// 2. findById(pk값)
		// - select * from 테이블명 where pk컬럼명 = pk값
		// 3. save(매개변수 or entity)
		// - insert into 테이블명 values(매개변수 or entity);
		// 4. delete(pk값)
		// - delete from 테이블명 where pk컬럼명 = pk값;
		
		// 메소드를 만드는 규칙
		// select * from member_entity where email=#{email} and pw=#{pw}
		// find + (테이블명)생략가능 + By + 컬럼명 + And(Or) + 컬럼명  
		
		// 리턴타입 -> entity객체 /  매개변수 - 작성 순서중요

	
	// 로그인 메소드

		
		// 위의 sql문 필요한 매개변수 : email, pw
		// 메소드를 만들 때는 반드시 카멜표기법으로 만들기
		

}

