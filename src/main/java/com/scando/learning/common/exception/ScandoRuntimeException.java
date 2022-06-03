package com.scando.learning.common.exception;

public class ScandoRuntimeException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final String message;

    public ScandoRuntimeException(String message) {
        super(message, (Throwable)null, false, false);
        this.message = message;
    }

    public ScandoRuntimeException(String message, Throwable cause) {
        super(message, cause, false, false);
        this.message = message;
    }

    public String toString() {
        return this.message;
    }

    public String getMessage() {
        return this.message;
    }
}
