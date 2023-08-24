package com.miraijr.karaoke.application.auth;

import com.miraijr.karaoke.application.auth.DTOs.AuthDTO;
import com.miraijr.karaoke.application.auth.DTOs.RefreshTokenDTO;
import com.miraijr.karaoke.application.auth.types.AuthResponse;
import com.miraijr.karaoke.application.user.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public AuthResponse handleLogin(@RequestBody @Valid AuthDTO account) {
        UserEntity user = authService.login(account);

        return new AuthResponse(user.getAccesstToken(), user.getRefreshToken());
    }

    @PutMapping("/refresh-token")
    public AuthResponse handleRefreshToken(@RequestBody @Valid RefreshTokenDTO refreshTokenDTO) throws Exception {
        AuthResponse authResponse = authService.refreshToken(refreshTokenDTO.getRefreshToken());

        return authResponse;
    }
}
