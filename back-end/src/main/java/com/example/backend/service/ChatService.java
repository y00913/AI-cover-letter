package com.example.backend.service;

import com.example.backend.vo.QuestionVo;

public interface ChatService {

    String sendMessageToGpt(QuestionVo questionVo);

}
