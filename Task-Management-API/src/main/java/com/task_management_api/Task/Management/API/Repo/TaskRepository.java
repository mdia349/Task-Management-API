package com.task_management_api.Task.Management.API.Repo;

import com.task_management_api.Task.Management.API.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
