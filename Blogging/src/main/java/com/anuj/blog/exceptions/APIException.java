package com.anuj.blog.exceptions;

public class APIException extends RuntimeException{

    public APIException(String message) {
        super(message);
    }

    public APIException() {
        super();
    }
}
