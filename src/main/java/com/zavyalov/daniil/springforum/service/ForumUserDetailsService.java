package com.zavyalov.daniil.springforum.service;

import com.zavyalov.daniil.springforum.converter.UserManager;
import com.zavyalov.daniil.springforum.entity.UserTableEntity;
import com.zavyalov.daniil.springforum.form.UserForm;
import com.zavyalov.daniil.springforum.repository.UserNeo4jRepository;
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
    private final UserPostgresRepository postgresRepository;
    private final UserManager userManager;
    private final UserNeo4jRepository neo4jRepository;

    ForumUserDetailsService(UserPostgresRepository postgresRepository, UserManager userManager, UserNeo4jRepository neo4jRepository) {
        this.postgresRepository = postgresRepository;
        this.userManager = userManager;
        this.neo4jRepository = neo4jRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTableEntity userTableEntity = postgresRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return new ForumUserDetails(userTableEntity);
    }

    public UserView createUser(UserForm form) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        form.setPassword(passwordEncoder.encode(form.getPassword()));
        UserTableEntity tableEntity = postgresRepository.save(userManager.formToNewEntity(form));
        neo4jRepository.save(userManager.tableEntityToNewGraphEntity(tableEntity));

        return userManager.entityToView(tableEntity);
    }

    public Optional<UserView> getUserById(Integer id) {
        Optional<UserTableEntity> res = postgresRepository.findById(id);
        return res.map(entity -> userManager.entityToView(entity));
    }

    public void deleteAll() {
        postgresRepository.deleteAll();
    }
}
