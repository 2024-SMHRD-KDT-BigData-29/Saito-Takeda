package com.smhrd.basic.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.basic.entity.BoardEntity;
import com.smhrd.basic.entity.UserEntity;

// 이메일로 회원 정보 조회
// select * from tb_user where user_email=?
// Optional은 주로 JPA 쿼리문을 통해 DB에서 데이터를 호출할때 만약 데이터가 없을때 null값을 피하기 위해 사용한다.

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	
	UserEntity findByUserEmail(String userEmail);

	UserEntity findByUserEmailAndUserPw(String userEmail, String userPw);

	void save(BoardEntity board);





///
	
	
	
	
	
	
	
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
		// select * from User_entity where email=#{email} and pw=#{pw}
		// find + (테이블명)생략가능 + By + 컬럼명 + And(Or) + 컬럼명  
		
		// 리턴타입 -> entity객체 /  매개변수 - 작성 순서중요
		// 메소드를 만들 때는 반드시 카멜표기법으로 만들기
		

}

