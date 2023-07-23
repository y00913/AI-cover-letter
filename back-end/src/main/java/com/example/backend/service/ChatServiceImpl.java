package com.example.backend.service;

import com.example.backend.client.ChatCompletionClient;
import com.example.backend.dto.ChatRequest;
import com.example.backend.dto.Message;
import com.example.backend.enums.ModelEnum;
import com.example.backend.vo.QuestionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.backend.enums.RoleEnum;

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
    public String sendMessageToGpt(QuestionVo questionVo) {
        String request = "질문에 해당하는 자기소개서를 내 정보를 포함해서 만들어줘. 질문은 \"" + questionVo.getQuestion() + "\"이고, 내 정보는 \"" + questionVo.getInformation() + "\"이야.";

        Message message =Message.builder()
                .role(RoleEnum.ROLE_USER.type())
                .content(request)
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

        log.info("request : " + request);
        log.info("response : " + result);

        return result;

    }

}
