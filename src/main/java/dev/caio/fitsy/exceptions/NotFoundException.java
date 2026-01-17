package dev.caio.fitsy.exceptions;


public class NotFoundException extends RuntimeException {
    
        public NotFoundException (String instance, Long id) {
            super(instance + " not found for user id: " + id);
        }
}
