package com.prwebsitebackend.dto;

import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.List;
@Data
public class UserBulletin {
    private Long id;
    private String title;
    private List<String> post_header;
    private List<String> post_content;
}
