package com.nghia.springsecuritydemo.service;

import com.nghia.springsecuritydemo.dto.request.SignInRequest;
import com.nghia.springsecuritydemo.dto.response.SignInResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService {
    SignInResponse signIn(SignInRequest request);
}
