package com.zavyalov.daniil.springforum.converter;

import com.zavyalov.daniil.springforum.entity.UserGraphEntity;
import com.zavyalov.daniil.springforum.entity.UserTableEntity;
import com.zavyalov.daniil.springforum.form.PostForm;
import com.zavyalov.daniil.springforum.form.UserForm;
import com.zavyalov.daniil.springforum.repository.UserPostgresRepository;
import com.zavyalov.daniil.springforum.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class UserManager {
    private UserPostgresRepository userPostgresRepository;

    public UserView entityToView(UserTableEntity entity) {
        return new UserView(entity.getId(), entity.getUsername(), entity.getEmail());
    }

    public UserTableEntity formToNewEntity(UserForm form) {
        return new UserTableEntity(form.getUsername(), form.getEmail(), form.getPassword());
    }

    public UserGraphEntity tableEntityToNewGraphEntity(UserTableEntity tableEntity) {
        return new UserGraphEntity(tableEntity.getId(), new HashSet<>(), new HashSet<>());
    }
}
