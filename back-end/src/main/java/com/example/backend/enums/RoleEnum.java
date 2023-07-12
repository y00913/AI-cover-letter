package com.example.backend.enums;

public enum RoleEnum {
    ROLE_USER("user"),
    ROLE_GPT("assistant");

    private final String type;
    RoleEnum(String type) {
        this.type = type;
    }

    public String type(){
        return type;
    }
}
