package com.example.homework.service;

import com.example.homework.repository.CommentRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class CommentService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }


}

