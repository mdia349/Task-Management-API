package com.task_management_api.Task.Management.API.Controller;

import com.task_management_api.Task.Management.API.DTO.UserDTO;
import com.task_management_api.Task.Management.API.Repo.UserRepository;
import com.task_management_api.Task.Management.API.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.registerUser(userDTO.getUsername(), userDTO.getPassword());
        return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            if (userService.loginUser(userDTO.getUsername(), userDTO.getPassword())) {
                return new ResponseEntity<>("User successfully logged in", HttpStatus.OK);
            }
        } else if (userRepository.findByUsername(userDTO.getUsername()) == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    }
}
