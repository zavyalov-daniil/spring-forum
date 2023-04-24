package com.zavyalov.daniil.springforum.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Set;

@Node("Post")
@Getter
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Property("title")
    private String title;

    @Setter
    @Property("text")
    private String text;

    @Property("creationTime")
    //@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    Date creationTime;

    @Setter
    @Relationship(type = "HAS_COMMENT", direction = Relationship.Direction.OUTGOING)
    private Set<PostEntity> parentPost;

    public PostEntity(String text, String title, Set<PostEntity> parentPost) {
        this.title = title;
        this.text = text;
        this.parentPost = parentPost;
    }
}
