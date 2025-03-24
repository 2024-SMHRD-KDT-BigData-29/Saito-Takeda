package com.smhrd.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.smhrd.basic.model.Board;
=======
import com.smhrd.basic.entity.Board;
>>>>>>> branch 'master' of https://github.com/2024-SMHRD-KDT-BigData-29/Saito-Takeda.git

<<<<<<< HEAD

public interface BoardRepositroy extends JpaRepository<Board, Long>{
=======
@Repository
public interface BoardRepositroy extends JpaRepository<Board, Integer>{
>>>>>>> branch 'master' of https://github.com/2024-SMHRD-KDT-BigData-29/Saito-Takeda.git

}
