package com.example.backend.controller;

import com.example.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gpt/v1/chat")
@RequiredArgsConstructor
public class ChatApiController {

    private final ChatService chatService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("test");
    }

    @GetMapping("/msg")
    public ResponseEntity<?> sendMessage(String question){
        String gptMessage = chatService.sendMessageToGpt(question);

        return ResponseEntity.ok(gptMessage);
    }

}
