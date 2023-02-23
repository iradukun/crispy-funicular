package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.BulletPostDTO;
import com.prwebsitebackend.dto.BulletinDTO;
import com.prwebsitebackend.model.BulletPost;
import com.prwebsitebackend.model.Bulletin;
import com.prwebsitebackend.model.PostComment;
import com.prwebsitebackend.model.User;
import com.prwebsitebackend.repository.BulletPostRepository;
import com.prwebsitebackend.repository.BulletinRepository;
import com.prwebsitebackend.repository.PostCommentRepository;
import com.prwebsitebackend.utils.LoggedInUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BulletinService {

    private final BulletinRepository bulletinRepository;
    private final LoggedInUser loggedInUser;
    private final BulletPostRepository bulletPostRepository;
    private final PostCommentRepository postCommentRepository;

    public BulletinService(BulletinRepository bulletinRepository, LoggedInUser loggedInUser, BulletPostRepository bulletPostRepository, PostCommentRepository postCommentRepository) {
        this.bulletinRepository = bulletinRepository;
        this.loggedInUser = loggedInUser;
        this.bulletPostRepository = bulletPostRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public Bulletin create_bulletin(BulletinDTO data) {

        Bulletin bulletin = new Bulletin();
        bulletin.setTitle(data.getName());
        bulletin.setPost_header(data.getHeader());
        bulletin.setPost_content(data.getContent());

        return bulletinRepository.save(bulletin);
    }

    public List<Bulletin> get_bulletins() {
        return bulletinRepository.findAll();
    }

    public Bulletin get_bulletin(String id) {
        return bulletinRepository.findById(Long.parseLong(id)).orElse(null);
    }

    public BulletPost createBulletPost(String id, BulletPostDTO data) {
        User user = loggedInUser.getLoggedInUser();
        Bulletin bullet = get_bulletin(id);
        //create bullet post
        BulletPost bulletPost = new BulletPost(data.getTitle(),data.getHorseHead(), data.getContent());
        //add bullet post to bulletPosts
        List<BulletPost> bulletPosts = bullet.getBulletPosts();
        bulletPosts.add(bulletPost);
        //add bullet post to bulletin
        bullet.setBulletPosts(bulletPosts);
        //save bulletin to bulletPost
        bulletPost.setBulletin(bullet);
        //set author of bullet post
        bulletPost.setUser(user);

        return bulletPostRepository.save(bulletPost);
    }

    public List<BulletPost> getBulletPosts(String id) {
        List<BulletPost> bulletPost = bulletPostRepository.findByBulletinId(Long.parseLong(id));
        if(bulletPost.isEmpty()){
            System.out.println("bulletPost is empty");
            return Collections.emptyList();
        }
        return bulletPost;
    }

    public List<PostComment> createPostComment(String id, PostComment data) {
        User user = loggedInUser.getLoggedInUser();
        Optional<BulletPost> bulletPost = bulletPostRepository.findById(Long.parseLong(id));
        if (bulletPost.isPresent()){
            List<PostComment> comments = bulletPost.get().getPostComments();
            comments.add(data);
            bulletPost.get().setPostComments(comments);
            data.setPost(bulletPost.get());
            data.setUser(user);
            postCommentRepository.save(data);
            return bulletPost.get().getPostComments();
        }else {
            return null;
        }
    }
}
