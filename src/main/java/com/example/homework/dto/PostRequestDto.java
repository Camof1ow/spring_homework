package com.example.homework.dto;

import com.example.homework.controller.UserController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;



@NoArgsConstructor
@Getter


public class PostRequestDto {
    @Column
    private String author;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;








    public PostRequestDto(String title, String content) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        this.author= username;
        this.title=title;
        this.content=content;

    }


}