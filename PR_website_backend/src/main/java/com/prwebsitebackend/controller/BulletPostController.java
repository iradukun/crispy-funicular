package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.BulletPostDTO;
import com.prwebsitebackend.dto.PostCommentDto;
import com.prwebsitebackend.model.BulletPost;
import com.prwebsitebackend.model.PostComment;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.BulletinService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulletPost/")
public class BulletPostController {
    private final BulletinService bulletinService;
    public BulletPostController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @PostMapping("{id}")
    public ResponseEntity<APIResponse> createBulletPost(@PathVariable String id, @RequestBody BulletPostDTO data) {
        ModelMapper modelMapper = new ModelMapper();
        BulletPost bullet = bulletinService.createBulletPost(id, data);
        if(bullet != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse("Created","Post Created successfully", modelMapper.map(bullet, BulletPostDTO.class)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Error","Post creation failed", null));
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse> getBulletPosts(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        TypeToken<List<BulletPostDTO>> token = new TypeToken<>() {
        };

        List<BulletPost> bullet = bulletinService.getBulletPosts(id);

        if (!bullet.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("success","fetched all bullet posts",modelMapper.map(bullet, token.getType())));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Error","Post retrieval failed", null));
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<APIResponse> getBulletPostsByBulletin(@PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        TypeToken<List<BulletPostDTO>> token = new TypeToken<>() {
        };

        List<BulletPost> bullet = bulletinService.get_bulletin(id).getBulletPosts();

        if (!bullet.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("success","fetched all bullet posts",modelMapper.map(bullet, token.getType())));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Error","Post retrieval failed", null));
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<APIResponse> addComment(@PathVariable String id, @RequestBody PostCommentDto data) {
        ModelMapper modelMapper = new ModelMapper();
        PostComment comment = modelMapper.map(data, PostComment.class);
        Object bulletComment = bulletinService.createPostComment(id, comment);
        if(bulletComment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("success", "Comment added successfully", bulletComment));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Error","failed To add Comment", null));
    }
}
