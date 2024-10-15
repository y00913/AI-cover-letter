package com.example.backend.enums;

public enum ModelEnum {
    MODEL_TURBO("gpt-3.5-turbo"),
    MODEL_4o("gpt-4o"),
    MODEL_4("gpt-4");

    private final String type;
    ModelEnum(String type) {
        this.type = type;
    }

    public String type(){
        return type;
    }
}
