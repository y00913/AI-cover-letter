package com.example.backend.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Message implements Serializable {

    private String role;
    private String content;

}
