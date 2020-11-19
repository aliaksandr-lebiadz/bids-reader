package com.elinext.bids.reader.exception;

public class JsonReadingException extends Exception {

    public JsonReadingException() {
    }

    public JsonReadingException(String message) {
        super(message);
    }

    public JsonReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonReadingException(Throwable cause) {
        super(cause);
    }
}
