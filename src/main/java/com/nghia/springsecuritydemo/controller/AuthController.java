package com.nghia.springsecuritydemo.controller;

import com.nghia.springsecuritydemo.constant.StatusResponse;
import com.nghia.springsecuritydemo.dto.request.SignInRequest;
import com.nghia.springsecuritydemo.dto.response.BaseResponse;
import com.nghia.springsecuritydemo.dto.response.SignInResponse;
import com.nghia.springsecuritydemo.service.AuthService;
import com.nghia.springsecuritydemo.utils.HttpRequestResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        try {
            log.info("START SIGN IN FROM IP: {}", HttpRequestResponseUtils.getClientIP());
            SignInResponse response = authService.signIn(request);
            log.info("SIGN IN -> REQUEST {} >>> SUCCESS", request);
            return new ResponseEntity<>(BaseResponse.builder()
                    .status(StatusResponse.SUCCESS)
                    .message("Thành công")
                    .data(response)
                    .build(), HttpStatus.OK);
        } catch (UsernameNotFoundException unfe) {
            log.error("SIGN IN -> REQUEST: {} >>> ERROR: ", request, unfe);
            return new ResponseEntity<>(BaseResponse.builder()
                    .status(StatusResponse.ERROR)
                    .message(unfe.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("SIGN IN -> REQUEST: {} >>> ERROR: ", request, ex);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp() {
        return new ResponseEntity<>(BaseResponse.builder()
                .status(StatusResponse.SUCCESS)
                .message("Thanh cong")
                .build(), HttpStatus.OK);
    }
}
