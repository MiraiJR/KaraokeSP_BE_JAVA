package com.miraijr.karaoke.application.auth.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RefreshTokenDTO {
    @NotNull(message = "Refresh token cannot be null")
    @NotBlank(message = "Refresh token cannot be blank")
    private String refreshToken;

    public RefreshTokenDTO(@NotNull(message = "Refresh token cannot be null") String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public RefreshTokenDTO() {
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
