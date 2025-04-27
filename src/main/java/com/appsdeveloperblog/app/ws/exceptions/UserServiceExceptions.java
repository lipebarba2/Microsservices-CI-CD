package com.appsdeveloperblog.app.ws.exceptions;

import java.io.Serial;

public class UserServiceExceptions extends RuntimeException{

    @Serial
    private final static long serialVersionUID = 1L;

    public UserServiceExceptions(String message) {
        super(message);
    }

    public UserServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
