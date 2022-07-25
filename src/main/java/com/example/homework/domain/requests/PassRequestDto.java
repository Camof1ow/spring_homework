package com.example.homework.domain.requests;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;



@NoArgsConstructor
@Getter


public class PassRequestDto {
    @Column(nullable = false)
    private boolean check;


    public PassRequestDto(boolean check) {
        this.check = check;
    }



}