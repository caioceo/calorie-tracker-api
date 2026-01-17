package dev.caio.fitsy.exceptions;


public class NotFoundException extends RuntimeException {
    
        public NotFoundException (String target, String source , Long id) {
            super(target + " not found for " + source + " id: " + id);
        }
}
