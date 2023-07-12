package com.example.backend.enums;

public enum ModelEnum {
    MODEL_TURBO("gpt-3.5-turbo");

    private final String type;
    ModelEnum(String type) {
        this.type = type;
    }

    public String type(){
        return type;
    }
}
