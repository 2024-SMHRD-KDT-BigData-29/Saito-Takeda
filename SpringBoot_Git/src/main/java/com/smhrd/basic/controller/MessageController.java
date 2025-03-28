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
import com.smhrd.basic.service.MessageService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 메시지 목록 조회
    @GetMapping("/board/message")
    public String getMessages(Model model, HttpSession session) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        if (loginEmail == null) {
            return "redirect:/user/login";
        }
        
        List<MessageDTO> receivedMessages = messageService.getMessagesByReceiver(loginEmail);
        List<MessageDTO> sentMessages = messageService.getMessagesBySender(loginEmail);
        
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


	//
	

}