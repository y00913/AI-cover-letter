package com.example.backend.service;

import com.example.backend.client.ChatCompletionClient;
import com.example.backend.dto.ChatRequest;
import com.example.backend.dto.Message;
import com.example.backend.vo.QuestionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.backend.enums.RoleEnum;

import java.util.Arrays;
import java.util.List;

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
        List<Message> messageList = getMessageList(questionVo);

        ChatRequest chatRequest = ChatRequest.builder()
                .model("gpt-4o-mini")
                .messages(messageList)
                .build();

        String result = chatCompletionClient
                .chatCompletions(chatRequest)
                .getChoices()
                .stream()
                .findFirst()
                .orElseThrow()
                .getMessage()
                .getContent();

        log.info("question : " + questionVo.getQuestion());
        log.info("information : " + questionVo.getInformation());
        log.info("result : " + result);

        return result;

    }

    private List<Message> getMessageList(QuestionVo questionVo) {
        List<Message> messageList = Arrays.asList(
                Message.builder()
                        .role(RoleEnum.ROLE_SYSTEM.type())
                        .content("You are a helpful assistant.")
                        .build(),

                Message.builder()
                        .role(RoleEnum.ROLE_USER.type())
                        .content("회사에 지원하는데 너의 도움이 필요해. 질문에 해당하는 자기소개서를 내 정보를 포함해서 만들어. 회사의 질문은 " + questionVo.getQuestion() + "이야")
                        .build(),

                Message.builder()
                        .role(RoleEnum.ROLE_GPT.type())
                        .content("네, 알겠습니다.")
                        .build(),

                Message.builder()
                        .role(RoleEnum.ROLE_USER.type())
                        .content("내 정보는 " + questionVo.getInformation() + "이야. 최소 300자 이상으로 작성해.")
                        .build(),

                Message.builder()
                        .role(RoleEnum.ROLE_GPT.type())
                        .content("네, 알겠습니다.")
                        .build(),

                Message.builder()
                        .role(RoleEnum.ROLE_USER.type())
                        .content("위 내용을 토대로 사람이 쓴 것 같은 자기소개서를 작성해. 내 말에 대답하지말고 바로 작성해.")
                        .build()
        );

        return messageList;
    }

}
