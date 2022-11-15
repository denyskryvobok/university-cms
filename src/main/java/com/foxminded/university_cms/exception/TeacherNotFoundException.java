package com.foxminded.university_cms.exception;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException() {
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
