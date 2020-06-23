package com.example.agentapp.model;

public class MessageForQueue {

    private MessageHeaders headers;
    private String body;

    public MessageHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MessageHeaders headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MessageForQueue() {
    }

    public MessageForQueue(MessageHeaders headers, String body) {
        this.headers = headers;
        this.body = body;
    }
}
