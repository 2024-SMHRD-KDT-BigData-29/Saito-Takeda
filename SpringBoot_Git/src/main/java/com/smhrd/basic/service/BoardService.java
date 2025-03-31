package com.smhrd.basic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.dto.UserDTO;
import com.smhrd.basic.entity.BoardEntity;
import com.smhrd.basic.entity.FavoriteEntity;
import com.smhrd.basic.entity.ProfileEntity;
import com.smhrd.basic.repository.BoardRepository;
import com.smhrd.basic.repository.FavoriteRepository;
import com.smhrd.basic.repository.ProfileRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public void save(BoardDTO boardDTO) {
        BoardEntity entity = BoardEntity.toBoardEntity(boardDTO);
        boardRepository.save(entity);
    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> entities = boardRepository.findAll();
        return entities.stream().map(this::entityToDtoWithProfile).collect(Collectors.toList());
    }

    @Transactional
    public List<BoardDTO> findByBtype(String btype) {
        List<BoardEntity> entities = boardRepository.findByBtype(btype);
        return entities.stream().map(this::entityToDtoWithProfile).collect(Collectors.toList());
    }

    @Transactional
    public BoardDTO findByBidx(int bidx) {
        BoardEntity boardEntity = boardRepository.findById(bidx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + bidx));
        Integer currentViews = boardEntity.getBviews();
        if (currentViews == null) {
            currentViews = 0; // null이면 0으로 초기화
        }
        boardEntity.setBviews(currentViews + 1); // 조회수 1 증가
        boardRepository.save(boardEntity);
        return entityToDtoWithProfile(boardEntity);
    }

    @Transactional
    public BoardDTO createBoardWithFile(BoardDTO boardDTO) {
        BoardEntity entity = BoardEntity.toBoardEntity(boardDTO);
        return entityToDtoWithProfile(boardRepository.save(entity));
    }

    @Transactional
    public BoardDTO getBoard(int bidx) {
        return findByBidx(bidx);
    }

    @Transactional
    public List<BoardDTO> getBoardsByType(String btype) {
        return findByBtype(btype);
    }

    @Transactional
    public BoardDTO updateBoard(int bidx, BoardDTO boardDTO, String userEmail) {
        BoardEntity entity = boardRepository.findById(bidx).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        if (!entity.getBwriter().equals(userEmail)) {
            throw new RuntimeException("게시글을 수정할 권한이 없습니다.");
        }
        entity.setBtitle(boardDTO.getBtitle());
        entity.setBcontent(boardDTO.getBcontent());
        entity.setBfile(boardDTO.getBfile());
        entity.setMonthlyRent(boardDTO.getMonthlyRent());
        entity.setManagementFee(boardDTO.getManagementFee());
        entity.setHouseType(boardDTO.getHouseType());
        entity.setAddress(boardDTO.getAddress());
        entity.setBudget(boardDTO.getBudget());
        entity.setUserPhoto(boardDTO.getUserPhoto());
        entity.setDesiredAddress(boardDTO.getDesiredAddress());
        return entityToDtoWithProfile(boardRepository.save(entity));
    }

    @Transactional
    public void deleteBoard(int bidx, String userEmail, String userRole) {
        BoardEntity entity = boardRepository.findById(bidx)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // 관리자(userRole = "a")이거나, 작성자와 현재 사용자가 동일한 경우에만 삭제 허용
        if (!"a".equals(userRole) && !entity.getBwriter().equals(userEmail)) {
            throw new RuntimeException("게시글을 삭제할 권한이 없습니다.");
        }
        boardRepository.delete(entity);
    }
    
 // 기존 deleteBoard 메서드 오버로드 유지 (관리자 여부 확인 없이 호출되는 경우)
    @Transactional
    public void deleteBoard(int bidx, String userEmail) {
        deleteBoard(bidx, userEmail, "u"); // 기본적으로 userRole을 "u"로 설정
    }

    @Transactional
    public boolean toggleFavorite(int bidx, String userEmail) {
        BoardEntity board = boardRepository.findById(bidx).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        Optional<FavoriteEntity> favorite = favoriteRepository.findByUserEmailAndBidx(userEmail, bidx);
        if (favorite == null) {
            FavoriteEntity newFavorite = new FavoriteEntity();
            newFavorite.setUserEmail(userEmail);
            newFavorite.setBidx(bidx);
            favoriteRepository.save(newFavorite);
            return true;
        } else {
            favoriteRepository.deleteByUserEmailAndBidx(userEmail, bidx);
            return false;
        }
    }

    public List<BoardDTO> findFavoriteBoards(String userEmail) {
        // 사용자가 찜한 게시글의 bidx 목록 조회
    	List<FavoriteEntity> favorites = favoriteRepository.findByUserEmail(userEmail);
        List<Integer> bidxList = favorites.stream()
                .map(FavoriteEntity::getBidx)
                .collect(Collectors.toList());

        // bidx로 게시글 조회
        return boardRepository.findByBidxIn(bidxList)
                .stream()
                .map(BoardDTO::fromEntity)
                .collect(Collectors.toList());
    }


    private BoardDTO entityToDtoWithProfile(BoardEntity entity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBidx(entity.getBidx());
        boardDTO.setBtype(entity.getBtype());
        boardDTO.setBtitle(entity.getBtitle());
        boardDTO.setBcontent(entity.getBcontent());
        boardDTO.setBfile(entity.getBfile());
        boardDTO.setCreatedAt(entity.getCreatedAt());
        boardDTO.setBviews(entity.getBviews());
        boardDTO.setBlikes(entity.getBlikes());
        boardDTO.setBwriter(entity.getBwriter());
        boardDTO.setMonthlyRent(entity.getMonthlyRent());
        boardDTO.setManagementFee(entity.getManagementFee());
        boardDTO.setHouseType(entity.getHouseType());
        boardDTO.setAddress(entity.getAddress());
        boardDTO.setBudget(entity.getBudget());
        boardDTO.setUserPhoto(entity.getUserPhoto());
        boardDTO.setDesiredAddress(entity.getDesiredAddress());
        boardDTO.setLeaseContractPath(entity.getLeaseContractPath());
        boardDTO.setCriminalRecordPath(entity.getCriminalRecordPath());

        // 작성자의 프로필 정보 추가
        ProfileDTO profileDTO = profileService.findByUserEmail(entity.getBwriter());
        if (profileDTO != null) {
            boardDTO.setUserMbti(profileDTO.getUserMbti());
            boardDTO.setProfileImg(profileDTO.getProfileImg());
        }

        // 작성자의 유저 정보 추가
        UserDTO userDTO = userService.findByUserEmail(entity.getBwriter());
        if (userDTO != null) {
            boardDTO.setUserNickname(userDTO.getUserNickname());
            String regnum = userDTO.getUserRegnum();
            if (regnum != null && regnum.length() >= 7) {
                String birthYearStr = regnum.substring(0, 2);
                int birthYear = Integer.parseInt(birthYearStr);
                birthYear = (birthYear >= 0 && birthYear <= 23) ? 2000 + birthYear : 1900 + birthYear;
                int currentYear = LocalDate.now().getYear();
                int age = currentYear - birthYear;
                boardDTO.setUserAge(age);
            }
        }

        return boardDTO;
    }
    
    
 // 로그인 후 메인화면에서 매칭성능향상
    @Transactional
    public List<BoardDTO> findByPartnerMbtiAndType(String userMbti, String btype) {
        List<ProfileEntity> profiles = profileRepository.findByPartnerMbti(userMbti);
        List<String> writerEmails = profiles.stream().map(ProfileEntity::getUserEmail).collect(Collectors.toList());
        List<BoardEntity> entities = boardRepository.findByBtypeAndBwriterIn(btype, writerEmails);
        return entities.stream().map(this::entityToDtoWithProfile).collect(Collectors.toList());
    }
    
    
}