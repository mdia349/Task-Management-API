package com.task_management_api.Task.Management.API.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.util.Date;

@Entity
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private Date dueDate;

    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Users user;
}
