package com.example.backend.controller;

import com.example.backend.service.ChatService;
import com.example.backend.vo.QuestionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gpt/v1/chat")
@RequiredArgsConstructor
public class ChatApiController {

    private final ChatService chatService;

    @PostMapping("/msg")
    public ResponseEntity<?> sendMessage(@RequestBody QuestionVo questionVo){
        String gptMessage = chatService.sendMessageToGpt(questionVo);

        return ResponseEntity.ok(gptMessage);
    }

}
