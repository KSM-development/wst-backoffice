package com.ksm.wstbackoffice.exception;

public class CheckValueException extends RuntimeException {
    public CheckValueException() {
    }

    public CheckValueException(String message) {
        super(message);
    }

    public CheckValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
