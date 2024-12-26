package com.sky.exception;

/**
 * 自定义登录异常
 */
public class LoginException extends RuntimeException {
    public LoginException(String message){
        super(message);
    }
    LoginException(String message, Throwable cause){
        super(message, cause);
    }
}
