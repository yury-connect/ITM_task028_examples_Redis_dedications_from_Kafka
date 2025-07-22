package com.example.redis_deduplicate.demo.exception;


public class PaymentValidationException extends Exception {
    public PaymentValidationException(String message) {
        super(message);
    }
}