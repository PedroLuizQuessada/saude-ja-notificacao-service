package com.example.saudejanotificacaoservice.exceptions;

public class BadArgumentException extends RuntimeException {
    public BadArgumentException(String message) {
        super(message);
    }
}
