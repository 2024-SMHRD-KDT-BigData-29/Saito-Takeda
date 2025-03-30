// MessageController.java
package com.smhrd.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.basic.dto.MessageDTO;
import com.smhrd.basic.dto.ProfileDTO;
import com.smhrd.basic.service.MessageService;
import com.smhrd.basic.service.ProfileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class MessageController {

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ProfileService profileService;

    // 메시지 목록 조회
    @GetMapping("/board/message")
    public String getMessages(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }

        List<MessageDTO> receivedMessages = messageService.getMessagesByReceiver(loginEmail);
        List<MessageDTO> sentMessages = messageService.getMessagesBySender(loginEmail);

        // 각 메시지에 대해 발신자와 수신자의 프로필 이미지 추가
        receivedMessages.forEach(message -> {
            ProfileDTO senderProfile = profileService.findByUserEmail(message.getSenderId());
            message.setSenderProfileImg(senderProfile != null ? senderProfile.getProfileImg() : "/images/default-profile.png");
        });

        sentMessages.forEach(message -> {
            ProfileDTO receiverProfile = profileService.findByUserEmail(message.getReceiverId());
            message.setReceiverProfileImg(receiverProfile != null ? receiverProfile.getProfileImg() : "/images/default-profile.png");
        });

        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("sentMessages", sentMessages);
        model.addAttribute("currentUser", loginEmail);

        return "/message/view";
    }

    // 메시지 작성 폼
    @GetMapping("/message/write")
    public String createMessageForm(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        
        model.addAttribute("messageDTO", new MessageDTO());
        model.addAttribute("senderId", loginEmail);
        return "/message/form";
    }

 // 메시지 전송
    @PostMapping("/message")
    public String sendMessage(MessageDTO messageDTO, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        messageDTO.setSenderId(loginEmail);
        messageDTO.setIsRead("N"); // 기본값: 읽지 않음
        messageService.sendMessage(messageDTO);
        return "redirect:/board";
    }

    // 메시지 삭제
    @PostMapping("/message/delete/{msgIdx}")
    public String deleteMessage(@PathVariable("msgIdx") int msgIdx, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        messageService.deleteMessage(msgIdx, loginEmail);
        return "redirect:/board/message";
    }

	
	

}