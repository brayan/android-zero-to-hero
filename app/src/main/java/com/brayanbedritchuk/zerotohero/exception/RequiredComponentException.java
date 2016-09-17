package com.brayanbedritchuk.zerotohero.exception;

public class RequiredComponentException extends Exception {

    public RequiredComponentException() {
    }

    public RequiredComponentException(String detailMessage) {
        super(detailMessage);
    }

    public RequiredComponentException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RequiredComponentException(Throwable throwable) {
        super(throwable);
    }
}
