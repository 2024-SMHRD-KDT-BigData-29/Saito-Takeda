package com.smhrd.basic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.basic.dto.BoardDTO;
import com.smhrd.basic.service.BoardService;

import lombok.RequiredArgsConstructor;

//프론트엔드에서 받은 정보들을 DTO에서 전달받아 처리하는 클래스 - Controller

@Controller
@RequiredArgsConstructor // 생성자 주입방식 어노테이션
@RequestMapping("/board")
public class BoardController {
	// RequestMapping을 통해서 Controller 최상위 경로를 지정하여 컨트롤러 안의 경로 단축
	// ex)/board/save

	// 생성자 주입방식으로 의존성을 주입받게됨
	private final BoardService boardService;
	
	@GetMapping("/save")
	public String saveForm() {
		return "boardSave";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute BoardDTO boardDTO) {
		System.out.println("boardDTO = " + boardDTO);
		boardService.save(boardDTO);
		
		return null;
	}
	
	@GetMapping("/")
	public String findAll(Model model) {
		List<BoardDTO> boardDTO = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		
		
		// DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다
		return "list";
	}
	
	
	
	
}