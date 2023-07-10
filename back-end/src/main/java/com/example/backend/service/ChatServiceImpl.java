package com.example.backend.service;

import com.example.backend.client.ChatCompletionClient;
import com.example.backend.domain.ChatRequest;
import com.example.backend.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatCompletionClient chatCompletionClient;
    private final static String ROLE_USER = "user";
    private final static String MODEL = "gpt-3.5-turbo";
    @Value("${gpt-api-key}")
    private String apiKey;

    @Override
    public String sendMessageToGpt(String question) {

        Message message = Message.builder()
                .role(ROLE_USER)
                .content(question)
                .build();

        ChatRequest chatRequest = ChatRequest.builder()
                .model(MODEL)
                .messages(Collections.singletonList(message))
                .build();

        String result = chatCompletionClient
                .chatCompletions("Bearer " + apiKey, chatRequest)
                .getChoices()
                .stream()
                .findFirst()
                .orElseThrow()
                .getMessage()
                .getContent();

        return result;

    }

}
