package br.com.template.api.exceptions;

public final class NotFoundException extends RuntimeException {
    public NotFoundException (String message) {
        super(message);
    }
}
