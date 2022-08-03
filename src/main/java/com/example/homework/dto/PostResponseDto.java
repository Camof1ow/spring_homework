package com.example.homework.dto;


import com.example.homework.domain.Post;
import com.example.homework.domain.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto extends Timestamped {
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    @JsonIgnore
    private String password;


    public PostResponseDto(LocalDateTime date,String title, String author,String content) {
        this.date = date;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostResponseDto(Post post) {
        this(post.getDate(),post.getTitle(), post.getAuthor(), post.getContent());
    }

}

