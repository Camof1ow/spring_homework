package com.example.homework.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;



@NoArgsConstructor
@Getter


public class PassResponseDto {
    @Column(nullable = false)
    private boolean check;


    public PassResponseDto(boolean check) {
        this.check = check;
    }


}