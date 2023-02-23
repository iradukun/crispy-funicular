package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="bulletin")
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ElementCollection
    private List<String> post_header;

    @ElementCollection
    private List<String> post_content;
    @JsonManagedReference
    @ManyToMany(
            mappedBy = "bulletins"
    )
    private Set<User> user;

    @JsonManagedReference
    @OneToMany(mappedBy = "bulletin",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BulletPost> bulletPosts;

    public Bulletin(String title, List<String> post_header, List<String> post_content) {
        this.title = title;
        this.post_header = post_header;
        this.post_content = post_content;
    }

    public Bulletin(String title, List<String> post_header, List<String> post_content, List<BulletPost> bulletPosts) {
        this.title = title;
        this.post_header = post_header;
        this.post_content = post_content;
        this.bulletPosts = bulletPosts;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public List<BulletPost> getBulletPosts() {
        return bulletPosts;
    }

    public void setBulletPosts(List<BulletPost> bulletPosts) {
        this.bulletPosts = bulletPosts;
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

    public List<String> getPost_header() {
        return post_header;
    }

    public void setPost_header(List<String> post_header) {
        this.post_header = post_header;
    }

    public List<String> getPost_content() {
        return post_content;
    }

    public void setPost_content(List<String> post_content) {
        this.post_content = post_content;
    }
}
