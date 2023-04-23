package com.zavyalov.daniil.springforum.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node("User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGraphEntity {
    @Id
    private Integer id;

    @Relationship(type = "LEFT_A_POST", direction = Relationship.Direction.OUTGOING)
    private Set<PostEntity> posts;

    @Relationship(type = "LEFT_A_COMMENT", direction = Relationship.Direction.OUTGOING)
    private Set<PostEntity> comments;
}
