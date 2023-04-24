package com.zavyalov.daniil.springforum.service;

import com.zavyalov.daniil.springforum.converter.PostManager;
import com.zavyalov.daniil.springforum.entity.PostEntity;
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
    CurrentUserFacade currentUserFacade;

    PostService(PostRepository postRepository, PostManager postManager, CurrentUserFacade currentUserFacade) {
        this.postRepository = postRepository;
        this.postManager = postManager;
        this.currentUserFacade = currentUserFacade;
    }

    public PostForm getPostForm() {
        return new PostForm();
    }

    public List<PostView> getAllPosts() {
        List<PostEntity> entityList = postRepository.findAll();
        List<PostView> res = new ArrayList<>();

        for (PostEntity post : entityList) {
            res.add(postManager.entityToView(post));
        }

        return res;
    }

    public Optional<PostView> findPostById(Long postId) {
        Optional<PostEntity> res = postRepository.findById(postId);
        return res.map(entity -> postManager.entityToView(entity));
    }

    public PostView savePost(PostForm postForm) {
        PostEntity entity = postManager.formToNewEntity(postForm);
        PostEntity savedEntity = postRepository.save(entity);
        postRepository.mergeUserAndPost(currentUserFacade.getCurrentUser().getUserId(), savedEntity.getId());

        return postManager.entityToView(savedEntity);
    }

    public PostView saveComment(PostForm postForm) {
        PostEntity entity = postManager.formToNewEntity(postForm);
        PostEntity savedEntity = postRepository.save(entity);
        postRepository.mergeUserAndPost(currentUserFacade.getCurrentUser().getUserId(), savedEntity.getId());

        return postManager.entityToView(savedEntity);
    }

    public Optional<PostView> updatePost(Long id, PostForm form) {
        Optional<PostEntity> entityOptional = postRepository.findById(id);
        if(entityOptional.isPresent()) {
            PostEntity entity = entityOptional.get();
            entity = postManager.updateEntity(entity, form);
            return Optional.of(postManager.entityToView(postRepository.save(entity)));
        } else {
            return Optional.empty();
        }
    }

    public void clearById(Long id) {
        Optional<PostEntity> entityOptional = postRepository.findById(id);
        if (entityOptional.isPresent()) {
            PostEntity entity = entityOptional.get();
            postRepository.save(postManager.clearEntity(entity));
        }
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public void test(PostForm postForm) {
        PostEntity entity = postManager.formToNewEntity(postForm);
        /*return postManager.entityToView(*/postRepository.mergeUserAndPost(6, 2L);//);
    }
}
