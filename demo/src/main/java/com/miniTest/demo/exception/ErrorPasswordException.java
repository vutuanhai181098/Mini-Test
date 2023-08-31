package com.miniTest.demo.exception;

public class ErrorPasswordException extends RuntimeException{
    public ErrorPasswordException(String message){
        super(message);
    }
}
