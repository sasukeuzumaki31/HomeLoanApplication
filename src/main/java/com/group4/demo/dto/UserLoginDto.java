package com.group4.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDto {
    @NotNull(message = "userId cannot be null")
    int userId;
    @NotNull(message = "Password cannot be null")
    String password;
}
