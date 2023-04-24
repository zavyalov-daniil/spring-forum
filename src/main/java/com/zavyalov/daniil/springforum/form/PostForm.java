package com.zavyalov.daniil.springforum.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
    @NotNull
    private String title;
    @NotNull
    private String text;
    private Long parentPostId;
    private Integer userId;
}