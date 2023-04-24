package com.zavyalov.daniil.springforum.controller;

import com.zavyalov.daniil.springforum.exception.PostNotFoundException;
import com.zavyalov.daniil.springforum.form.PostForm;
import com.zavyalov.daniil.springforum.service.PostService;
import com.zavyalov.daniil.springforum.view.PostView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(path = "/new")
    @ResponseStatus(HttpStatus.OK)
    public PostForm getPostForm() {
        return service.getPostForm();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PostView> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostView getPostById(@PathVariable Long postId) throws PostNotFoundException {
        return service.findPostById(postId).orElseThrow(PostNotFoundException::new);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostView createPost(@RequestBody PostForm postForm) {
        return service.savePost(postForm);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(path = "/{postId}")
    public PostView updatePostText(@PathVariable Long postId, @RequestBody PostForm postForm) throws PostNotFoundException {
        return service.updatePost(postId, postForm).orElseThrow(PostNotFoundException::new);
    }

    @DeleteMapping(path = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) {
        service.clearById(postId);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPosts() {
        service.deleteAll();
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public void test(@RequestBody PostForm postForm) {
        service.test(postForm);
    }
}
