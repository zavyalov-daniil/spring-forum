package com.zavyalov.daniil.springforum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserFacade {

    public ForumUserDetails getCurrentUser() {
        return (ForumUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
