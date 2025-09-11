package com.task_management_api.Task.Management.API.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String role;
}
