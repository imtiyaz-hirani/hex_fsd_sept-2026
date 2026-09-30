package com.helpdesk.service;

import com.helpdesk.exception.InvalidCredentialsException;
import com.helpdesk.model.User;
import com.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        List<User> list =  userRepository.login(username,password);
        if(list == null || list.isEmpty())
            throw new InvalidCredentialsException("Invalid credentials");

        return list.getFirst();
    }
}
