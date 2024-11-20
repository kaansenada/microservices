package com.kaansenada.cards.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, String fieldName, String fieldValue) {
        super(resource + " not found with " + fieldName + ":" + fieldValue);
    }
}
