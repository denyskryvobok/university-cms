package com.foxminded.university_cms.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException() {
    }

    public GroupNotFoundException(String message) {
        super(message);
    }
}
