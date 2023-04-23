package com.zavyalov.daniil.springforum.service;

import com.zavyalov.daniil.springforum.entity.UserEntity;
import com.zavyalov.daniil.springforum.repository.UserPostgresRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ForumUserDetailsService implements UserDetailsService {
    private final UserPostgresRepository repository;

    ForumUserDetailsService(UserPostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return new ForumUserDetails(userEntity);
    }
}
