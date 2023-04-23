package com.zavyalov.daniil.springforum.converter;

import com.zavyalov.daniil.springforum.entity.PostTableEntity;
import com.zavyalov.daniil.springforum.form.PostForm;
import com.zavyalov.daniil.springforum.repository.PostRepository;
import com.zavyalov.daniil.springforum.view.PostView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class PostManager {
    private PostRepository postRepository;

    public PostView entityToView(PostTableEntity entity) {
        return new PostView(entity.getId(), entity.getTitle(), entity.getText(), entity.getCreationTime());
    }

    public PostTableEntity formToNewEntity(PostForm form) {
        HashSet<PostTableEntity> parent = new HashSet<>();
        postRepository.findById(form.getParentPostId()).ifPresent(parent::add);
        return new PostTableEntity(form.getText(), form.getTitle(), parent);
    }

    public PostTableEntity updateEntity(PostTableEntity postTableEntity, PostForm postForm) {
        postTableEntity.setTitle(postForm.getTitle());
        postTableEntity.setText(postForm.getText());
        HashSet<PostTableEntity> parent = new HashSet<>();
        postRepository.findById(postForm.getParentPostId()).ifPresent(parent::add);
        postTableEntity.setParentPost(parent);
        return postTableEntity;
    }

    public PostTableEntity clearEntity(PostTableEntity entity) {
        entity.setText("");
        entity.setTitle("");
        return entity;
    }
}
