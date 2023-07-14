package com.example.backend.service;

import com.example.backend.client.ChatCompletionClient;
import com.example.backend.domain.ChatRequest;
import com.example.backend.domain.Message;
import com.example.backend.enums.ModelEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.backend.enums.RoleEnum;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatCompletionClient chatCompletionClient;
    @Value("${gpt-api-key}")
    private String apiKey;

    @Override
    public String sendMessageToGpt(String question) {

        Message message =Message.builder()
                .role(RoleEnum.ROLE_USER.type())
                .content(question)
                .build();

        ChatRequest chatRequest = ChatRequest.builder()
                .model(ModelEnum.MODEL_TURBO.type())
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
