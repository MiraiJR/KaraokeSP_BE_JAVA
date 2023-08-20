package com.miraijr.karaoke.application.auth;

import com.miraijr.karaoke.application.auth.DTOs.AuthDTO;
import com.miraijr.karaoke.application.jwt.JwtService;
import com.miraijr.karaoke.application.user.UserEntity;
import com.miraijr.karaoke.application.user.UserRepository;
import com.miraijr.karaoke.application.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public UserEntity login(AuthDTO account) {
        UserEntity user = checkUsername(account.getUsername());

        if(!user.getPassword().equals(account.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password is not valid!");
        }

        String accessToken = jwtService.generateAccessToken(user.getId());

        user.setAccesstToken(accessToken);

        user = userService.updateUser(user);
        return user;
    }

    public UserEntity checkUsername(String username) {
        UserEntity user = userService.findUserByUsername(username);

        if(user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password is not valid!");
        }

        return user;
    }
}