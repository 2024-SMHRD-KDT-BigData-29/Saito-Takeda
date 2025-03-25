package com.smhrd.basic.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smhrd.basic.entity.UserEntity;
import com.smhrd.basic.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //  1. 회원가입
    public String register(UserEntity user) {
        if (userRepository.findByUsername(user.getUserName()).isPresent()) {
            return "이미 존재하는 사용자입니다.";
        }
        userRepository.save(user);
        return "회원가입 성공!";
    }

    //  2. 비밀번호 찾기
    public String findPassword(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "비밀번호는: " + user.getUserPw())
                .orElse("해당 이메일이 없습니다.");
    }

    //  3. 회원탈퇴
    public String deleteUser(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "회원탈퇴 완료.";
        }
        return "존재하지 않는 사용자입니다.";
    }
}