//package com.smhrd.basic.entity;
//
//import java.time.LocalDateTime;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "tb_session")
//public class SessionEntity {
//	// 시간정보를 다룰 수 있또록 하는
//	// 세션 엔터티가 될 거 같음
//	// 다른 entity 테이블에서 부모테이블로 상속해줄예정
//	
//	
//	// 생성됐을 때 시간 정보를 담고

//	@CreationTimestamp
//	@Column(updatable = false) // 수정시에는 관여안하게끔
//	private LocalDateTime createdTime;
//	
//	// 수정됐을 때 시간 정보를 담는거
//	@UpdateTimestamp
//	@Column(insertable = false) // 입력(인설트)시 관여 안하게끔
//	private LocalDateTime updatedTime;
//	///
//	
//}