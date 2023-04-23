package com.zavyalov.daniil.springforum.service;

import com.zavyalov.daniil.springforum.converter.PostManager;
import com.zavyalov.daniil.springforum.entity.PostTableEntity;
import com.zavyalov.daniil.springforum.form.PostForm;
import com.zavyalov.daniil.springforum.repository.PostRepository;
import com.zavyalov.daniil.springforum.view.PostView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    PostRepository postRepository;
    PostManager postManager;

    PostService(PostRepository postRepository, PostManager postManager) {
        this.postRepository = postRepository;
        this.postManager = postManager;
    }

    public PostForm getPostForm() {
        return new PostForm();
    }

    public List<PostView> getAllPosts() {
        List<PostTableEntity> entityList = postRepository.findAll();
        List<PostView> res = new ArrayList<>();

        for (PostTableEntity post : entityList) {
            res.add(postManager.entityToView(post));
        }

        return res;
    }

    public Optional<PostView> findPostById(Long postId) {
        Optional<PostTableEntity> res = postRepository.findById(postId);
        return res.map(entity -> postManager.entityToView(entity));
    }

    public PostView save(PostForm postForm) {
        PostTableEntity entity = postManager.formToNewEntity(postForm);
        return postManager.entityToView(postRepository.save(entity));
    }

    public Optional<PostView> updatePost(Long id, PostForm form) {
        Optional<PostTableEntity> entityOptional = postRepository.findById(id);
        if(entityOptional.isPresent()) {
            PostTableEntity entity = entityOptional.get();
            entity = postManager.updateEntity(entity, form);
            return Optional.of(postManager.entityToView(postRepository.save(entity)));
        } else {
            return Optional.empty();
        }
    }

    public void clearById(Long id) {
        Optional<PostTableEntity> entityOptional = postRepository.findById(id);
        if (entityOptional.isPresent()) {
            PostTableEntity entity = entityOptional.get();
            postRepository.save(postManager.clearEntity(entity));
        }
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }
}
