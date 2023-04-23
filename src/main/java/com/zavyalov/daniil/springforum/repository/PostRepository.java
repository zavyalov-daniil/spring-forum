package com.zavyalov.daniil.springforum.repository;

import com.zavyalov.daniil.springforum.entity.PostTableEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<PostTableEntity, Long> {

}
