package com.example.redis_deduplicate.demo.exception;


public class PaymentValidationException extends Exception {

    public PaymentValidationException(String message) {
        super(message);
    }

//    public PaymentValidationException(String message, Throwable cause) {
    public PaymentValidationException(String message, Exception e) {
        super(message, e);
    }
}