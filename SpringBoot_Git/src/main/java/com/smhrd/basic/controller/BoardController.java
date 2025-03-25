package com.smhrd.basic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.basic.service.BoardService;

import lombok.RequiredArgsConstructor;

//프론트엔드에서 받은 정보들을 DTO에서 전달받아 처리하는 클래스 - Controller

@Controller
@RequestMapping("/board")
public class BoardController {
	// RequestMapping을 통해서 Controller 최상위 경로를 지정하여 컨트롤러 안의 경로 단축
	// ex)/board/save

	// 생성자 주입방식으로 의존성을 주입받게됨
	private BoardService boardService;
	
	@GetMapping("/save")
	public String saveForm() {
		return "boardSave";
	}
	

	
	
	
	
}