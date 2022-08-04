package com.example.homework.model;

import com.example.homework.controller.UserController;
import com.example.homework.dto.PostRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
    private String title;
    private String content;


    public static UserController userController;

    private String author;


    @Builder
    public Post(String title, String content, String author) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        this.title = title;
        this.content = content;
        this.author = username;

    }

    public static Post toEntity(PostRequestDto dto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(username)
                .build();
    }

    public void update(PostRequestDto requestDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = username;
    }




}
