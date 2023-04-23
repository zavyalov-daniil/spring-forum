package com.zavyalov.daniil.springforum.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private Integer id;
    private String username;
    private String email;
}
