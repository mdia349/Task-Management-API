package com.task_management_api.Task.Management.API.Service;

import com.task_management_api.Task.Management.API.Entity.Users;
import com.task_management_api.Task.Management.API.Repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try {
            Users user = userRepository.findByUsername(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getRoles().stream()
                            .map(role -> "ROLE_" + role)
                            .toArray(String[]::new))
                    .build();

        }
        catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

    }
}
