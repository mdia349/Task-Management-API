package com.task_management_api.Task.Management.API.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private Set<String> roles;
}
