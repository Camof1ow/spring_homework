package com.example.homework.domain.requests;

import com.example.homework.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter


public class PostRequestDto {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;

    public PostRequestDto(String title, String author, String content,String password) {
        this.author=author;
        this.title=title;
        this.content=content;
        this.password=password;


    }

    public PostRequestDto(Post post) {
        this(post.getPassword(), post.getTitle(), post.getAuthor(), post.getContent());
    }


}