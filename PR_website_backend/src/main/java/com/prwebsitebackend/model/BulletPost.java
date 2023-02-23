package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BulletPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String horseHead;
    String content;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "bulletin_id")
    Bulletin bulletin;
    @JsonManagedReference
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<PostComment> postComments;

    public BulletPost(String title, String horseHead, String content) {
        this.title = title;
        this.horseHead = horseHead;
        this.content = content;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHorseHead() {
        return horseHead;
    }

    public void setHorseHead(String horseHead) {
        this.horseHead = horseHead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
