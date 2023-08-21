package com.miraijr.karaoke.application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findUserById(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user;
    }

    public UserEntity findUserByUsername(String username) {
        UserEntity user = userRepository.findUserByUsername(username);

        return user;
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }
}
