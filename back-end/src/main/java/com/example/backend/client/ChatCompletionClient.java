package com.example.backend.client;

import com.example.backend.domain.ChatRequest;
import com.example.backend.domain.ChatResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chat", url = "https://api.openai.com/v1/")
public interface ChatCompletionClient {

    @Headers("Content-type: application/json")
    @PostMapping("/chat/completions")
    ChatResponse chatCompletions(@RequestHeader("Authorization") String apiKey, ChatRequest request);

}
