package com.foxminded.university_cms.exception;

public class SubjectNotFoundException extends RuntimeException{

    public SubjectNotFoundException() {
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
