package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long publicRelations;
    private Long publicRelationsForm;
    @JsonManagedReference
    @ManyToMany(mappedBy = "teams")
    private Set<User> user = new HashSet<>();
    private Long teamLeader;
    private Long accountActivityForm;

    @JsonManagedReference
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Schedule> schedules;

//    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    List<ConnectionAccount> teamAccounts;

    public Long getAccountActivityForm() {
        return accountActivityForm;
    }

    public void setAccountActivityForm(Long accountActivityForm) {
        this.accountActivityForm = accountActivityForm;
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

    public Long getPublicRelations() {
        return publicRelations;
    }

    public void setPublicRelations(Long publicRelations) {
        this.publicRelations = publicRelations;
    }

    public Long getPublicRelationsForm() {
        return publicRelationsForm;
    }

    public void setPublicRelationsForm(Long publicRelationsForm) {
        this.publicRelationsForm = publicRelationsForm;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Long getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(Long teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
