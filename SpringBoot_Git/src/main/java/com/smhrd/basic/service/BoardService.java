package com.smhrd.basic.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.entity.BoardEntity;
import com.smhrd.basic.entity.FavoriteEntity;
import com.smhrd.basic.repository.BoardRepositroy;
import com.smhrd.basic.repository.FavoriteRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepositroy boardRepository;

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private FavoriteRepository favoriteRepository;
    
    
 // 파일 저장 경로 
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

 // 게시글 작성 (파일 업로드 포함)
    @Transactional
    public BoardDTO createBoardWithFile(BoardDTO boardDTO) throws IOException {
        // 파일 업로드 처리 (bfile)
        String filePath = saveFile(boardDTO.getFile());
        boardDTO.setBfile(filePath);

        // 방 찾기용 사진 업로드 처리 (userPhoto)
        if (boardDTO.getBtype().equals("mate") && boardDTO.getUserPhotoFile() != null && !boardDTO.getUserPhotoFile().isEmpty()) {
            String userPhotoPath = saveFile(boardDTO.getUserPhotoFile());
            boardDTO.setUserPhoto(userPhotoPath);
        }

        // DTO -> Entity 변환 및 저장
        BoardEntity entity = dtoToEntity(boardDTO);
        entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        entity.setBviews(0);
        entity.setBlikes(0);
        BoardEntity savedEntity = boardRepository.save(entity);
        return entityToDto(savedEntity);
    }
    
 // 회원 프로필 정보 조회
    public ProfileDTO getUserProfile(String userEmail) {
        return profileService.findByUserEmail(userEmail);
    }
    
 // 파일 저장 로직
    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String savedFileName = UUID.randomUUID().toString() + fileExtension;
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File destFile = new File(UPLOAD_DIR + savedFileName);
        file.transferTo(destFile);
        return "/uploads/" + savedFileName;
    }

	// 게시글 조회 (단일)
    public BoardDTO getBoard(int bidx) {
        BoardEntity entity = boardRepository.findById(bidx)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + bidx));
        // 조회수 증가
        entity.setBviews(entity.getBviews() + 1);
        boardRepository.save(entity);
        return entityToDto(entity);
    }

    // 게시글 목록 조회 (유형별)
    public List<BoardDTO> getBoardsByType(String btype) {
        List<BoardEntity> entities = boardRepository.findByBtype(btype);
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // 게시글 수정
    @Transactional
    public BoardDTO updateBoard(int bidx, BoardDTO boardDTO, String userEmail) throws IOException {
        BoardEntity entity = boardRepository.findById(bidx)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + bidx));

        if (!entity.getUserEmail().equals(userEmail)) {
            throw new SecurityException("본인이 작성한 게시글만 수정할 수 있습니다.");
        }

        // 파일 업로드 처리 (새 파일이 있으면 기존 파일 대체)
        if (boardDTO.getFile() != null && !boardDTO.getFile().isEmpty()) {
            String filePath = saveFile(boardDTO.getFile());
            entity.setBfile(filePath);
        }

        entity.setBtitle(boardDTO.getBtitle());
        entity.setBcontent(boardDTO.getBcontent());
        entity.setBtype(boardDTO.getBtype());
        BoardEntity updatedEntity = boardRepository.save(entity);
        return entityToDto(updatedEntity);
    }

    
    // 게시글 삭제
    @Transactional
    public void deleteBoard(int bidx, String userEmail) {
        BoardEntity entity = boardRepository.findById(bidx)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + bidx));
        
        // 작성자 확인
        if (!entity.getUserEmail().equals(userEmail)) {
            throw new SecurityException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }

        boardRepository.delete(entity);
    }

    // 게시글 찜하기/취소
    @Transactional
    public boolean toggleFavorite(int bidx, String userEmail) {
        BoardEntity board = boardRepository.findById(bidx)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다: " + bidx));

        Optional<FavoriteEntity> favorite = favoriteRepository.findByBidxAndUserEmail(bidx, userEmail);
        if (favorite.isPresent()) {
            // 이미 찜한 경우 -> 취소
            favoriteRepository.delete(favorite.get());
            return false; // 찜 취소됨
        } else {
            // 찜 안 한 경우 -> 추가
            FavoriteEntity newFavorite = new FavoriteEntity();
            newFavorite.setBidx(bidx);
            newFavorite.setUserEmail(userEmail);
            favoriteRepository.save(newFavorite);
            return true; // 찜 추가됨
        }
    }

 // DTO -> Entity 변환
    private BoardEntity dtoToEntity(BoardDTO dto) {
        BoardEntity entity = new BoardEntity();
        entity.setBidx(dto.getBidx());
        entity.setBtype(dto.getBtype());
        entity.setBtitle(dto.getBtitle());
        entity.setBcontent(dto.getBcontent());
        entity.setBfile(dto.getBfile());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setBviews(dto.getBviews());
        entity.setBlikes(dto.getBlikes());
        entity.setUserEmail(dto.getBwriter());
        // 룸메 찾기용 필드
        entity.setMonthlyRent(dto.getMonthlyRent());
        entity.setManagementFee(dto.getManagementFee());
        entity.setHouseType(dto.getHouseType());
        // 방 찾기용 필드
        entity.setBudget(dto.getBudget());
        entity.setUserPhoto(dto.getUserPhoto());
        return entity;
    }

 // Entity -> DTO 변환
    private BoardDTO entityToDto(BoardEntity entity) {
        return new BoardDTO(
                entity.getBidx(),
                entity.getBtype(),
                entity.getBtitle(),
                entity.getBcontent(),
                entity.getBfile(),
                null, // file은 조회 시 필요 없음
                entity.getCreatedAt(),
                entity.getBviews(),
                entity.getBlikes(),
                entity.getUserEmail(),
                entity.getMonthlyRent(),
                entity.getManagementFee(),
                entity.getHouseType(),
                entity.getBudget(),
                entity.getUserPhoto(),
                null // userPhotoFile은 조회 시 필요 없음
        );
    }
    
    // 찜한 게시글 조회
    public List<BoardDTO> findFavoriteBoards(String userEmail) {
        List<FavoriteEntity> favorites = favoriteRepository.findByUserEmail(userEmail);
        List<Integer> boardIds = favorites.stream()
                .map(FavoriteEntity::getBidx)
                .collect(Collectors.toList());
        return boardRepository.findAllById(boardIds).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

 // 게시글 목록 조회
    public List<BoardDTO> findAll() {
        return boardRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    
    
}
