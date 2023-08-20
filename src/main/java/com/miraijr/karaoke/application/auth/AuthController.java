package com.miraijr.karaoke.application.auth;

import com.miraijr.karaoke.application.auth.DTOs.AuthDTO;
import com.miraijr.karaoke.application.user.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserEntity handleLogin(@RequestBody @Valid AuthDTO account) {
        UserEntity user = authService.login(account);

        return user;
    }
}
