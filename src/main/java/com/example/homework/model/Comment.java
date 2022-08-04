package com.example.homework.model;

import com.example.homework.controller.UserController;
import com.example.homework.dto.CommentRequestDto;
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
public class Comment extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private  Long id;

    private Long postId;

    private String content;

    private String author;

    public static UserController userController;


    @Builder
    public Comment(String content, Long postId,String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;

    }

    public static Comment toEntity(CommentRequestDto dto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return Comment.builder()
                .postId(dto.getPostId())
                .content(dto.getContent())
                .author(((UserDetails)principal).getUsername())
                .build();
    }



}
