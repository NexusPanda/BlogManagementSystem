package com.Blog.BlogManagementSystem.Exception;

public class APIException extends RuntimeException{
    private String message;

    public APIException(String message) {
        super(message);
    }
}
