package com.prwebsitebackend.repository;

import com.prwebsitebackend.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
}
