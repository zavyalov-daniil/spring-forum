package com.zavyalov.daniil.springforum.repository;

import com.zavyalov.daniil.springforum.entity.UserGraphEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserNeo4jRepository extends Neo4jRepository<UserGraphEntity, Integer> {

}
