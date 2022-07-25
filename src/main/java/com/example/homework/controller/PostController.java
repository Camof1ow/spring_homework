package com.example.homework.controller;

import com.example.homework.domain.Post;
import com.example.homework.domain.PostRepository;
import com.example.homework.domain.requests.PassRequestDto;
import com.example.homework.domain.requests.PostRequestDto;

import com.example.homework.domain.response.PassResponseDto;
import com.example.homework.domain.response.PostResponseDto;
import com.example.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController //Json
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.

    @GetMapping("/api/posts")
    public List<Post> list(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @PostMapping("/api/posts")
    public Post create(@RequestBody PostRequestDto dto){
        return postRepository.save(Post.toEntity(dto));
    }


    @GetMapping("/api/posts/{id}")
    public PostResponseDto findOne(@PathVariable Long id){
        Post post = postService.findOne(id);
        return new PostResponseDto(post);
    }

    @PostMapping("/api/posts/{id}")
    public PassResponseDto checkPass (@RequestBody String password, @PathVariable Long id){
        Post post = postService.findOne(id);
        new PostResponseDto(post);
        String pass1 = post.getPassword();
        return Objects.equals(pass1, password) ? new PassResponseDto(true) : new PassResponseDto(false);
    }




    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }



    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }


}