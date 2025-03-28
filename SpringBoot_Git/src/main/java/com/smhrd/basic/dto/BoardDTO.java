package com.smhrd.basic.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

    // 글 식별자 
    private int bidx;

    // 글 유형 
    private String btype;

    // 글 제목 
    private String btitle;

    // 글 내용 
    private String bcontent;

    // 글 첨부파일 
    private String bfile;
    
    // 파일 업로드를 위한 필드 추가
    private MultipartFile file;

    // 글 작성일자 
    private Timestamp createdAt;

    // 글 조회수 
    private Integer bviews;

    // 글 좋아요수 
    private Integer blikes;

    // 글 작성자 
    private String bwriter;
    
    // 룸메찾기용 추가 필드
    private Integer monthlyRent; // 월세
    private Integer managementFee; // 관리비
    private String houseType; // 집의 형태 (예: 아파트, 오피스텔, 원룸)
    private String address; // 주소 (룸메 찾기용, 추가)

    // 방찾기용 추가 필드
    private Integer budget; // 한달 희망 예산
    private String userPhoto; // 자신의 사진 경로
    private MultipartFile userPhotoFile; // 자신의 사진 업로드용
    private String desiredAddress; // 희망 주소지 (방 찾기용, 추가)

    // 조회 시 표시할 작성자 프로필 정보
    private String userNickname; // 닉네임 (UserEntity에서)
    private Integer userAge; // 나이 (UserEntity에서 계산)
    private String userMbti; // MBTI (ProfileEntity에서)
    private String profileImg; // 프로필 사진 (ProfileEntity에서)
    
    private MultipartFile leaseContract; // 임대차 계약서 (룸메 찾기용)
    private String leaseContractPath; // 업로드된 임대차 계약서 경로
    private MultipartFile criminalRecord; // 범죄경력회보서 (방 찾기용)
    private String criminalRecordPath; // 업로드된 범죄경력회보서 경로
    
}