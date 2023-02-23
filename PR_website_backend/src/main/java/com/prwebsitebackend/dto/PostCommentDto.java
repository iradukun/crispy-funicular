package com.prwebsitebackend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostCommentDto {
    private Long id;

    private String title;

    private String horseHead;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
