package com.zavyalov.daniil.springforum.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String username;
    private String email;
    private String password;
}
