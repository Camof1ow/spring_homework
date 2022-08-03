package com.example.homework.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;



@NoArgsConstructor
@Getter


public class PassRequestDto {
    @Column(nullable = false)
    private boolean check;

    @Column(nullable = false)
    private String password;


    public PassRequestDto(String password) {
        this.password=password;
        System.out.println(password);
    }


}