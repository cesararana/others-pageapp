package com.cesararana.exe.pageapp.application.exceptions;

public class PageAppException extends Exception {

    public PageAppException() {
    }

    public PageAppException(String message) {
        super(message);
    }

    public PageAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageAppException(Throwable cause) {
        super(cause);
    }

    public PageAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
