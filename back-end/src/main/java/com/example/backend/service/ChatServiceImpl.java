package com.example.backend.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatgptService chatgptService;

    @Override
    public String sendMessageToGpt(String message) {
        String result = chatgptService.sendMessage(message);

        return result;
    }

}
