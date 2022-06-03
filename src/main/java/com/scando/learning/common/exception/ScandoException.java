package com.scando.learning.common.exception;

public class ScandoException extends Exception{
    private static final long serialVersionUID = 1L;
    private final String message;

    public ScandoException(String message) {
        super(message, (Throwable)null, false, false);
        this.message = message;
    }

    public ScandoException(String message, Throwable cause) {
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
