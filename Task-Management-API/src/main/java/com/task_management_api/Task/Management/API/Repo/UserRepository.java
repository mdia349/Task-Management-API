package com.task_management_api.Task.Management.API.Repo;

import com.task_management_api.Task.Management.API.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    public Users findByUsername(String username);
}
