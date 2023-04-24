package com.zavyalov.daniil.springforum.controller;

import com.zavyalov.daniil.springforum.exception.PostNotFoundException;
import com.zavyalov.daniil.springforum.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new AppError(
                        request.getRequestURI(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal server error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new AppError(
                        request.getRequestURI(),
                        HttpStatus.NOT_FOUND.value(),
                        "User not found"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchPostNotFoundException(PostNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new AppError(
                        request.getRequestURI(),
                        HttpStatus.NOT_FOUND.value(),
                        "Post not found"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new AppError(
                        request.getRequestURI(),
                        HttpStatus.NOT_FOUND.value(),
                        "Username not found"),
                HttpStatus.NOT_FOUND);
    }
}
