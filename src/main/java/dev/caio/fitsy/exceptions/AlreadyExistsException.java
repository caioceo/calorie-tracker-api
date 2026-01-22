package dev.caio.fitsy.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String instance) {
        super( instance + " já está registrado no sistema.");
    }
}
