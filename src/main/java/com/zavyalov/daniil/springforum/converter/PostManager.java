package com.zavyalov.daniil.springforum.converter;

import com.zavyalov.daniil.springforum.entity.PostEntity;
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

    public PostView entityToView(PostEntity entity) {
        return new PostView(entity.getId(), entity.getTitle(), entity.getText(), entity.getCreationTime());
    }

    public PostEntity formToNewEntity(PostForm form) {
        HashSet<PostEntity> parent = new HashSet<>();
        postRepository.findById(form.getParentPostId()).ifPresent(parent::add);
        return new PostEntity(form.getText(), form.getTitle(), parent);
    }

    public PostEntity updateEntity(PostEntity postEntity, PostForm postForm) {
        postEntity.setTitle(postForm.getTitle());
        postEntity.setText(postForm.getText());
        HashSet<PostEntity> parent = new HashSet<>();
        postRepository.findById(postForm.getParentPostId()).ifPresent(parent::add);
        postEntity.setParentPost(parent);
        return postEntity;
    }

    public PostEntity clearEntity(PostEntity entity) {
        entity.setText("");
        entity.setTitle("");
        return entity;
    }
}
