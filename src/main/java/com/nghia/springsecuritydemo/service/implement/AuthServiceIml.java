package com.nghia.springsecuritydemo.service.implement;

import com.nghia.springsecuritydemo.dto.UserLoginPrinciple;
import com.nghia.springsecuritydemo.dto.request.SignInRequest;
import com.nghia.springsecuritydemo.dto.response.SignInResponse;
import com.nghia.springsecuritydemo.entity.Role;
import com.nghia.springsecuritydemo.entity.User;
import com.nghia.springsecuritydemo.respository.UserRepository;
import com.nghia.springsecuritydemo.security.JwtService;
import com.nghia.springsecuritydemo.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceIml implements AuthService {

    @Value("${application.security.password-salt}")
    private String passwordSalt;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceIml(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Sai ten dang nhap hoac mat khau"));

        if (!passwordEncoder.matches(request.getPassword() + passwordSalt, user.getPassword()))
            throw new UsernameNotFoundException("Sai ten dang nhap hoac mat khau");

        String token = jwtService.generateToken(UserLoginPrinciple.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .reference(user.getReference())
                        .gender(user.isGender())
                        .roles(user.getRoles().stream()
                                .map(Role::getRole).toList())
                .build());
        return SignInResponse.builder()
                .token(token)
                .build();
    }
}
