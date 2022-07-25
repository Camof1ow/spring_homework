package com.example.homework.domain.check;

import com.example.homework.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter


public class PassCheckDto {
    @Column(nullable = false)
    private String check;


    public PassCheckDto(String check) {
        this.check = check;
    }



}