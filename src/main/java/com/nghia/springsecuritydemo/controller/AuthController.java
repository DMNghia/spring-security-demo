package com.nghia.springsecuritydemo.controller;

import com.nghia.springsecuritydemo.constant.StatusResponse;
import com.nghia.springsecuritydemo.dto.response.BaseResponse;
import com.nghia.springsecuritydemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn() {
        return new ResponseEntity<>(BaseResponse.builder()
                .status(StatusResponse.SUCCESS)
                .message("Thành công")
                .build(), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp() {
        return new ResponseEntity<>(BaseResponse.builder()
                .status(StatusResponse.SUCCESS)
                .message("Thanh cong")
                .build(), HttpStatus.OK);
    }
}
