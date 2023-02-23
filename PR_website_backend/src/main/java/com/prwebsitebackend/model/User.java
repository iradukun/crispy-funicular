package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.*;
import com.prwebsitebackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "app_users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(
            unique = true,
            nullable = false
    )
    private String username;
    private String id;
    @Column(
            nullable = false
    )
    private String password;
    private String role;
//    @JsonManagedReference
//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Bulletin> bulletins;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Backup> backup;

//    @OneToOne(
//            mappedBy = "user",
//            cascade = CascadeType.ALL
//    )
//    @JsonIgnoreProperties("user")
//    private BackUpSettings backUpSettings;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "management_permissions_id",
            referencedColumnName = "id"
    )
    private ManagementPermissions managementPermissions;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<BulletPost> bulletPosts;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<PostComment> postComments;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "userId")},
            inverseJoinColumns = @JoinColumn(name = "team_id",referencedColumnName = "id")
    )
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Set<Team> teams= new HashSet<>();
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_bulletin",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "userId")},
            inverseJoinColumns = @JoinColumn(name = "bulletin_id",referencedColumnName = "id")
    )
    private Set<Bulletin>bulletins = new HashSet<>();

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    public User(String username,String id,String password, String role) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public List<Backup> getBackup() {
        return backup;
    }

    public void setBackup(List<Backup> backup) {
        this.backup = backup;
    }

    public ManagementPermissions getManagementPermissions() {
        return managementPermissions;
    }

    public void setManagementPermissions(ManagementPermissions managementPermissions) {
        this.managementPermissions = managementPermissions;
    }

    public List<BulletPost> getBulletPosts() {
        return bulletPosts;
    }

    public void setBulletPosts(List<BulletPost> bulletPosts) {
        this.bulletPosts = bulletPosts;
    }

    public Set<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(Set<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

    //    public List<Bulletin> getBulletins() {
//        return bulletins;
//    }
//
//    public void setBulletins(List<Bulletin> bulletins) {
//        this.bulletins = bulletins;
//    }
}
