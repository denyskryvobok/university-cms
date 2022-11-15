package com.foxminded.university_cms.exception;

public class CalendarNotFoundException extends RuntimeException{
    public CalendarNotFoundException() {
    }

    public CalendarNotFoundException(String message) {
        super(message);
    }
}
