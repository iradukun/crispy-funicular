package com.prwebsitebackend.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChangePasswordDto {
    private String oldpassword;
    private String newpassword;
    private String newpasswordConfirm;

    public String getOldpassword() {
        return oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public String getNewpasswordConfirm() {
        return newpasswordConfirm;
    }
}
