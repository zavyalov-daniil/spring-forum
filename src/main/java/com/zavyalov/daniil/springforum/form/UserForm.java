package com.zavyalov.daniil.springforum.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @NotNull
    @Size(min = 3, max = 30)
    private String username;
    @NotNull
    private String email;
    @NotNull
    @Size(min = 3, max = 30)
    private String password;
}
