package com.zavyalov.daniil.springforum.service;

import com.zavyalov.daniil.springforum.entity.UserTableEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserFacade {
    UserTableEntity entity = new UserTableEntity(800, "Test user", "test@email.com", "test_pass");

    public ForumUserDetails getCurrentUser() {
        //return (ForumUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ForumUserDetails(entity);
    }
}
