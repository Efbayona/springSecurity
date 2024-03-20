package com.cursos.springsecurity.auth.auth.security.service;

import com.cursos.springsecurity.auth.auth.dto.Permissions;
import com.cursos.springsecurity.auth.auth.security.service.impl.UserDetailsImpl;
import com.cursos.springsecurity.auth.user.entity.User;
import com.cursos.springsecurity.auth.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with userName: " + userName);
        }
        List<Permissions> permissions = userRepository.findPermissionsByUserName(userName);
        return UserDetailsImpl.build(user, permissions);
    }
}
