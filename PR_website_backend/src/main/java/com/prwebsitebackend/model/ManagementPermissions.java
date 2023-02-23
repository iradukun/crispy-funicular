package com.prwebsitebackend.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ManagementPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "boolean default false")
    private boolean accessRights;
    @Column(columnDefinition = "boolean default false")
    private boolean setPermissions;
    @Column(columnDefinition = "boolean default false")
    private boolean probabilitySettingPermission;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "managementPermissions")
    private User user;

    public ManagementPermissions() {
    }

    public ManagementPermissions(boolean accessRights, boolean setPermissions, boolean probabilitySettingPermission) {
        this.accessRights = accessRights;
        this.setPermissions = setPermissions;
        this.probabilitySettingPermission = probabilitySettingPermission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAccessRights() {
        return accessRights;
    }

    public void setAccessRights(boolean accessRights) {
        this.accessRights = accessRights;
    }

    public boolean isSetPermissions() {
        return setPermissions;
    }

    public void setSetPermissions(boolean setPermissions) {
        this.setPermissions = setPermissions;
    }

    public boolean isProbabilitySettingPermission() {
        return probabilitySettingPermission;
    }

    public void setProbabilitySettingPermission(boolean probabilitySettingPermission) {
        this.probabilitySettingPermission = probabilitySettingPermission;
    }
    public User getUser() {
            return user;
    }
    public void setUser(User user) {
            this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
