package com.zavyalov.daniil.springforum.controller;

import com.zavyalov.daniil.springforum.form.UserForm;
import com.zavyalov.daniil.springforum.service.ForumUserDetailsService;
import com.zavyalov.daniil.springforum.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    ForumUserDetailsService userDetailsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new")
    public UserForm getUserForm() {
        return new UserForm();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserView createUser(UserForm user) {
        return userDetailsService.createUser(user);
    }
}
