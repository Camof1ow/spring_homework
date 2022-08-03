package com.example.homework.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String passwordConfirm;
    private boolean admin = false;
    private String adminToken = "";

}