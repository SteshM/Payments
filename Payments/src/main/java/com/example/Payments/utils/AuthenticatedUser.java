package com.example.Payments.utils;


import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {
    public static String username(){
        return SecurityContextHolder.getContext().getAuthentication().getName();

    }
}
