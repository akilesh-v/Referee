package com.pickyourtrial.interview.referee.common.exceptions;

public class FailedDependencyException extends RuntimeException{
    public FailedDependencyException(String message) {
        super(message);
    }
}
