package com.agro.agro.exceptions;

public class SpringAgroException extends RuntimeException {
    public SpringAgroException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringAgroException(String exMessage) {
        super(exMessage);
    }
}
