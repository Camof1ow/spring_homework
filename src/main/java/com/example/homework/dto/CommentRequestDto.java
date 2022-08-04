package com.example.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;


    @Getter

    public class CommentRequestDto {

        @Column(nullable = false)
        private Long postId;
        @Column
        private String author;
        @Column(nullable = false)
        private String content;

        public void CommentRequestDto(Long postId, String content){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            this.author= username;
            this.content=content;
            this.postId= postId;

        }
    }


