package com.task_management_api.Task.Management.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TaskManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApiApplication.class, args);
	}

}
