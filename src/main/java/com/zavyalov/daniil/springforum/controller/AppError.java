package com.zavyalov.daniil.springforum.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    @JsonProperty("path")
    private String path;
    @JsonProperty("StatusCode")
    private int StatusCode;
    @JsonProperty("message")
    private String message;
}
