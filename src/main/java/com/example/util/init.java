package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class init {
    @Autowired
    private PasswordEncoder passwordEncoder;
}
