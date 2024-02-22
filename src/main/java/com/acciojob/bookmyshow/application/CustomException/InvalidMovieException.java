package com.acciojob.bookmyshow.application.CustomException;

public class InvalidMovieException extends Exception{
    public InvalidMovieException(String message) {
        super(message);
    }
}
