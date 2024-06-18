package com.example.backend.client;

import com.example.backend.dto.ChatRequest;
import com.example.backend.dto.ChatResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chat", url = "${gpt-url}")
public interface ChatCompletionClient {

    @Headers("Content-type: application/json")
    @PostMapping("/chat/completions")
    ChatResponse chatCompletions(ChatRequest request);

}
