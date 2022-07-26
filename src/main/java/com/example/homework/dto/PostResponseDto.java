package com.example.homework.dto;


import com.example.homework.model.Post;
import com.example.homework.model.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public PostResponseDto(String title, String author, String content) {

        this.title = title;
        this.author = author;
        this.content = content;
    }

    public PostResponseDto(Post post) {
        this(post.getTitle(), post.getAuthor(), post.getContent());
    }

}

