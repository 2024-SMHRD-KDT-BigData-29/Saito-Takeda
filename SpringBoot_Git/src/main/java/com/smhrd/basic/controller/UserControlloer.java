package com.smhrd.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.basic.entity.MemberEntity;
import com.smhrd.basic.model.Member;
import com.smhrd.basic.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserControlloer {
	// 회원관리 컨트롤러 !!
	
	@Autowired
	UserRepository repository;
	
	@PostMapping("/member/login.do")
	public String login(String email, String pw, HttpSession session) { // email, pw 외 null값
		
		System.out.println("email : "+email +" pw : " + pw);
		// select * from member_entity where email=#{email} and pw=#{pw}
		
		MemberEntity entity = repository.findByEmailAndPw(email, pw);
		session.setAttribute("member", entity);
		
		return "redirect:/";
	}
	
	@PostMapping("/member/join.do")
	public String join(Member member) { // email, pw, tel, address
		
		System.out.println(member.toString());
		
		MemberEntity entity = new MemberEntity(member);
		
		// 메소드에 넘겨줄 객체는 vo객체가 아니라 entity객체를 넘겨줘야함
		repository.save(entity);
		
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index() {
		
		return "index"; // .html 
	}
	
	
}
