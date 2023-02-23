package com.prwebsitebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManagementPermissionsDto {
    private Long id;
    private boolean accessRights;
    private boolean setPermissions;
    private boolean probabilitySettingPermission;
}
