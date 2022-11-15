package com.foxminded.university_cms.exception;

public class TimetableNotFoundException extends RuntimeException{

    public TimetableNotFoundException() {
    }

    public TimetableNotFoundException(String message) {
        super(message);
    }
}
