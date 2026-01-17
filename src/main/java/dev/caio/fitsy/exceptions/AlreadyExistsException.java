package dev.caio.fitsy.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String instance) {
        super( instance + " is already registered for this request");
    }
}
