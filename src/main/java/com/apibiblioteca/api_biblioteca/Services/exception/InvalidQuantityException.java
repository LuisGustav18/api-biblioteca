package com.apibiblioteca.api_biblioteca.Services.exception;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String msg){
            super(msg);
        }
}
