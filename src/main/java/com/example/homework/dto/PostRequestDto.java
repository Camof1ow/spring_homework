package com.example.homework.dto;

import com.example.homework.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;



@NoArgsConstructor
@Getter


public class PostRequestDto {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;


    public PostRequestDto(String title, String content) {
        this.title=title;
        this.content=content;


    }

    public PostRequestDto(Post post) {
        this(post.getTitle(), post.getContent());
    }


}