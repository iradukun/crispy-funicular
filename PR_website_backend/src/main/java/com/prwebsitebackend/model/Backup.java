package com.prwebsitebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Backup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String fileName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String fileUrl;

    private Date date = new Date(System.currentTimeMillis());

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToOne
//    @JoinColumn(name = "backup_id")
//    private BackUpSettings backUpSettings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Backup(String fileName, String fileUrl, BackUpSettings backUpAddSettings) {
//        this.fileName = fileName;
//        this.fileUrl = fileUrl;
//        this.backUpSettings = backUpAddSettings;
//    }

    public Backup(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
