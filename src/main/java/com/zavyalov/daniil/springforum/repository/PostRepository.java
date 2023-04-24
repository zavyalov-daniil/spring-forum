package com.zavyalov.daniil.springforum.repository;

import com.zavyalov.daniil.springforum.entity.PostEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<PostEntity, Long> {
    /*    @Query("MATCH(u:User) " +
            "where u.userId=$postId " +
            "with(u) " +
            "create(p:Post { text: $text, title: $title }) <- [:LEFT_A_POST] - (u) ")
    void saveUserPost(@Param("userId") Integer userId, @Param("text") String text, @Param("title") String title);
        А ведь этот запрос прекрасно работал в Neo4j Workspace...
    */
    @Query("MATCH(u:User) " +
            "where u.userId= $userId " +
            "with(u) " +
            "match(p:Post) " +
            "where ID(p)= $postId " +
            "with u,p " +
            "MERGE (u)-[:LEFT_A_POST]->(p) ")
    void mergeUserAndPost(@Param("userId") Integer userId, @Param("postId") Long postId);

    @Query("MATCH(u:User) " +
            "where u.userId= $userId " +
            "with(u) " +
            "match(p:Post) " +
            "where ID(p)= $postId " +
            "with u,p " +
            "MERGE (u)-[:LEFT_A_COMMENT]->(p) ")
    void mergeUserAndComment(@Param("userId") Integer userId, @Param("postId") Long postId);
}
