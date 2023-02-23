package com.prwebsitebackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class LoginLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long log_id;
    private Long userId;
    private String username;
    private String IP;
    private String browser;
    private Date loginDate;

    public LoginLogs(Long userId, String username, String IP, String browser, Date loginDate) {
        this.userId = userId;
        this.username = username;
        this.IP = IP;
        this.browser = browser;
        this.loginDate = loginDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
