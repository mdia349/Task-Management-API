package com.task_management_api.Task.Management.API.Service;

import com.task_management_api.Task.Management.API.Entity.Users;
import com.task_management_api.Task.Management.API.Repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users registerUser(String username, String password) {

        Users user = new Users();

        String hashedPassword = passwordEncoder.encode(password);

        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRoles(Set.of("USER"));

        return userRepository.save(user);

    }

    public Boolean loginUser(String username, String password) {

        Users user = userRepository.findByUsername(username);

        return passwordEncoder.matches(password, user.getPassword());
    }
}
