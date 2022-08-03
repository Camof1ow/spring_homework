package com.example.homework.controller;

import com.example.homework.ResEntity;
import com.example.homework.dto.PassRequestDto;
import com.example.homework.dto.PostRequestDto;
import com.example.homework.jwt.HeaderTokenExtractor;
import com.example.homework.model.Post;
import com.example.homework.repository.PostRepository;
import com.example.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.example.homework.ResEntity.StatusEnum.OK;

@RequiredArgsConstructor
@RestController //Json
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

    private final HeaderTokenExtractor headerTokenExtractor;

    private final HttpServletRequest httpServletRequest;


    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.





    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findById() {
        List<Post> pos = postRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
        return ResponseEntity.status(HttpStatus.OK).body(pos);
    }


    @PostMapping("/api/auth/posts")
    public ResEntity create(@RequestBody PostRequestDto dto){

        Post post = postRepository.save(Post.toEntity(dto));
        return new ResEntity(post,OK);
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){
        Post post = postService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
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

    public HeaderTokenExtractor getHeaderTokenExtractor() {
        return headerTokenExtractor;
    }
}