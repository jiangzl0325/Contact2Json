package com.example.demo;

public class Contract2Json {

    private final long id;
    private final String content;

    public Contract2Json(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
