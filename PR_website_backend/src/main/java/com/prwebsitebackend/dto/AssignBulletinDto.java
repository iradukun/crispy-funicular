package com.prwebsitebackend.dto;

import java.util.Set;

public class AssignBulletinDto {
    Long userId;
    Set<Long> bulletinIds;

    public Set<Long> getBulletinIds() {
        return bulletinIds;
    }

    public void setBulletinIds(Set<Long> bulletinIds) {
        this.bulletinIds = bulletinIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
