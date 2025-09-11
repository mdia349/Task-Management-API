package com.task_management_api.Task.Management.API.Controller;

import com.task_management_api.Task.Management.API.DTO.UserDTO;
import com.task_management_api.Task.Management.API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {

        userService.registerUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {

        userService.authenticateUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User logged in successfully");
    }
}
