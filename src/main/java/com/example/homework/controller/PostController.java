package com.example.homework.controller;

import com.example.homework.ResEntity;
import com.example.homework.UserDetailsImpl;
import com.example.homework.dto.CommentRequestDto;
import com.example.homework.dto.PostRequestDto;
import com.example.homework.jwt.HeaderTokenExtractor;
import com.example.homework.model.Comment;
import com.example.homework.model.Post;
import com.example.homework.repository.CommentRepository;
import com.example.homework.repository.PostRepository;
import com.example.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.homework.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.homework.ResEntity.StatusEnum.OK;

@RequiredArgsConstructor
@RestController //Json
public class PostController {
    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final CommentService commentService;


    private final PostService postService;

    private UserDetailsImpl userDetails;





    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.





    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findById() {
        List<Post> pos = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return new ResponseEntity<>(pos,HttpStatus.OK);
    }


    @PostMapping("/api/auth/posts")
    public ResEntity create(@RequestBody PostRequestDto dto){

        Post post = postRepository.save(Post.toEntity(dto));
        return new ResEntity(post,OK);
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        Post post = postService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }


    @PutMapping("/api/auth/posts/{id}")
    public ResEntity updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {

        return new ResEntity(postService.update(id, requestDto),OK);
    }
    @DeleteMapping("/api/auth/post/{id}")
    public ResEntity deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new ResEntity(true,OK);
    }


    @PostMapping("/api/auth/comment")
    public ResEntity create(@RequestBody CommentRequestDto dto){
        System.out.println(dto.getAuthor()+dto.getPostId()+dto.getContent());

        commentRepository.save(Comment.toEntity(dto));
        return new ResEntity(dto,OK);
    }


}