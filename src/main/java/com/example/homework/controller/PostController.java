package com.example.homework.controller;

import com.example.homework.ResEntity;
import com.example.homework.domain.Post;
import com.example.homework.domain.PostRepository;
import com.example.homework.dto.PassRequestDto;
import com.example.homework.dto.PostRequestDto;

import com.example.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.example.homework.ResEntity.StatusEnum.OK;

@RequiredArgsConstructor
@RestController //Json
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.



    @GetMapping("/api/posts")
    public ResEntity findById() {
        List<Post> pos = postRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        return new ResEntity(pos,OK);
    }


    @PostMapping("/api/posts")
    public ResEntity create(@RequestBody PostRequestDto dto){
        Post post = postRepository.save(Post.toEntity(dto));
        return new ResEntity(post,OK);
    }

    @GetMapping("/api/posts/{id}")
    public ResEntity findOne(@PathVariable Long id){
        Post post = postService.findOne(id);
        return new ResEntity(post,OK);
    }

    @PostMapping("/api/posts/{id}")
    public ResEntity checkPass (@RequestBody PassRequestDto password, @PathVariable Long id){
        String pass =  password.getPassword();// 바디에 입력한값 가져오기
        Post post = postService.findOne(id);
        String pass1 = post.getPassword();
        Objects.equals(pass, pass1);
        return new ResEntity(Objects.equals(pass, pass1),OK);
    }
    @PutMapping("/api/posts/{id}")
    public ResEntity updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {

        return new ResEntity(postService.update(id, requestDto),OK);
    }
    @DeleteMapping("/api/posts/{id}")
    public ResEntity deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new ResEntity(true,OK);
    }
}