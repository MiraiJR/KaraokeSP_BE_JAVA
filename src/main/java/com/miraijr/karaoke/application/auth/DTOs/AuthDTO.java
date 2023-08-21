package com.miraijr.karaoke.application.auth.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthDTO {
    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password has 8 characters at least")
    private String password;

    public AuthDTO(@NotNull(message = "Username cannot be null") String username, @NotNull(message = "Password cannot be null") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
