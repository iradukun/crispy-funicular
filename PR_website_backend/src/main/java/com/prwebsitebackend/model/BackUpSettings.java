package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BackUpSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    String time;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getTime() {
          return time;
     }

     public void setTime(String time) {
          this.time = time;
     }

     public User getUser() {
          return user;
     }

     public void setUser(User user) {
          this.user = user;
     }

}
