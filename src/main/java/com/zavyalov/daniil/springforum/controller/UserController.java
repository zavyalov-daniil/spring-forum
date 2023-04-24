package com.zavyalov.daniil.springforum.controller;

import com.zavyalov.daniil.springforum.exception.UserNotFoundException;
import com.zavyalov.daniil.springforum.form.UserForm;
import com.zavyalov.daniil.springforum.service.user.ForumUserDetailsService;
import com.zavyalov.daniil.springforum.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    ForumUserDetailsService userDetailsService;

    public UserController(ForumUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/new")
    public UserForm getUserForm() {
        return new UserForm();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserView createUser(@RequestBody UserForm user) {
        return userDetailsService.createUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserView getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
        return userDetailsService.getUserById(id).orElseThrow(UserNotFoundException::new);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPosts() {
        userDetailsService.deleteAll();
    }
}
