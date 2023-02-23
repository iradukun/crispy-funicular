package com.prwebsitebackend.dto;

import com.prwebsitebackend.model.PostComment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BulletPostDTO {
    private Long id;
    private String title;
    private String horseHead;
    private String content;

    private LocalDateTime createdAt;

    List<PostCommentDto> postComments;


    public BulletPostDTO(Long id, String title, String horseHead, String content) {
        this.id = id;
        this.title = title;
        this.horseHead = horseHead;
        this.content = content;
    }

    public BulletPostDTO() {
    }

}
