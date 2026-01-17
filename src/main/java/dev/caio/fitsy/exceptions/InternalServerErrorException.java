package dev.caio.fitsy.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(){
        super("Internal Server Error");
    }
}
