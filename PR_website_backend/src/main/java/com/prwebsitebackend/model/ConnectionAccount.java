package com.prwebsitebackend.model;

import javax.persistence.*;

@Entity
public class ConnectionAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(
            nullable = false
    )
    private String name;
    private String memberId;
    @Column(
            nullable = false
    )
    private String password;
    private String authority;

    @ManyToOne
    @JoinColumn(
            name = "team_id"
    )
    private Team team;

    public ConnectionAccount() {
    }

    public ConnectionAccount(String name, String memberId, String password, String authority) {
        this.name = name;
        this.memberId = memberId;
        this.password = password;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
