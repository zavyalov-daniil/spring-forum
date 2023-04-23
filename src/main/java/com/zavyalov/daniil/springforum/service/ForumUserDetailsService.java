package com.zavyalov.daniil.springforum.service;

import com.zavyalov.daniil.springforum.converter.UserManager;
import com.zavyalov.daniil.springforum.entity.UserTableEntity;
import com.zavyalov.daniil.springforum.form.UserForm;
import com.zavyalov.daniil.springforum.repository.UserPostgresRepository;
import com.zavyalov.daniil.springforum.view.UserView;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForumUserDetailsService implements UserDetailsService {
    private final UserPostgresRepository repository;
    private final UserManager userManager;

    ForumUserDetailsService(UserPostgresRepository repository, UserManager userManager) {
        this.repository = repository;
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTableEntity userTableEntity = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return new ForumUserDetails(userTableEntity);
    }

    public UserView createUser(UserForm form) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        UserTableEntity entity = repository.save(userManager.formToNewEntity(form));

        return userManager.entityToView(entity);
    }

    public Optional<UserView> getUserById(Integer id) {
        Optional<UserTableEntity> res = repository.findById(id);
        return res.map(entity -> userManager.entityToView(entity));
    }
}
