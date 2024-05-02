package com.example.exception;

public class InternalServerErrorException extends ExceptionCustom {

    public InternalServerErrorException(Object errors) {
        super("UNEXPECTED ERROR OCCURRED", errors);
    }

}
