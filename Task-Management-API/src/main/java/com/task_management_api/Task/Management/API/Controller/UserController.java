package com.task_management_api.Task.Management.API.Controller;

import com.task_management_api.Task.Management.API.DTO.UserDTO;
import com.task_management_api.Task.Management.API.Entity.Users;
import com.task_management_api.Task.Management.API.Repo.UserRepository;
import com.task_management_api.Task.Management.API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.registerUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {

        if (userService.loginUser(userDTO.getUsername(), userDTO.getPassword()))
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User logged in successfully");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
    }
}
