package com.prwebsitebackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class BulletinDTO {
    Long id;
    String name;
    List<String> header;
    List<String> content;
    List<BulletPostDTO> bulletPosts;
}
