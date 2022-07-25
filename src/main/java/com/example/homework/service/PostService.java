package com.example.homework.service;

import com.example.homework.domain.PostRepository;
import com.example.homework.domain.Post;

import com.example.homework.domain.requests.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@RequiredArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class PostService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final PostRepository postRepository;


    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        post1.update(requestDto);
        return post1.getId();
    }

    @Transactional
    public Long delete(Long id, PostRequestDto requestDto) {
        Post post1 = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        post1.delete(requestDto);
        return post1.getId();
    }

    public Post findOne(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
    }


}

