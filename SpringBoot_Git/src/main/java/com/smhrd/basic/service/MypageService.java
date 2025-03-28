package com.smhrd.basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.basic.repository.MessageRepository;
import com.smhrd.basic.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class MypageService {
	
	@Autowired
    private UserRepository userRepository;
//
}
