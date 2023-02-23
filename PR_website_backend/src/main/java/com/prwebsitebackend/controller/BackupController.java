package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.BackupSettingsDto;
import com.prwebsitebackend.model.BackUpSettings;
import com.prwebsitebackend.model.Backup;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.BackupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/backup")
public class BackupController {
    private final BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping
    public APIResponse getAllBackup() {
        List<Backup> backups = backupService.getAllBackup();
        if(!backups.isEmpty()){
            return new APIResponse("success", "Backups fetched successfully",backups);
        }
        return new APIResponse("error", "No backups found", backups);
    }
    @PostMapping()
    public APIResponse createBackup(@Value("${spring.datasource.username}") String dbUsername, @Value("${spring.datasource.password}") String dbPassword
    ) throws SQLException, IOException, ClassNotFoundException, MessagingException {
        //String backupFileName = "" + System.currentTimeMillis()+".sql";
        boolean backup = backupService.backup(dbUsername, dbPassword, "prtestdb");
        if (backup) {
            return new APIResponse("success", "Backup created successfully", true);
        } else {
            return new APIResponse("error", "Backup failed. Check If you set your backup settings correctly", false);
        }
    }

    @GetMapping("/restore/{id}")
    public APIResponse restore(
            @PathVariable("id") Long backupId,
            @Value("${spring.datasource.username}") String dbUsername, @Value("${spring.datasource.password}") String dbPassword
    ) throws SQLException, IOException, ClassNotFoundException, MessagingException {

        boolean currentBackup = backupService.backup(dbUsername, dbPassword, "prtestdb");
        boolean restoration = backupService.restore(backupId, dbUsername, dbPassword, "prtestdb");
        if(currentBackup && restoration){
            return new APIResponse("success","Backup restore successful", true);
        }else{
            return new APIResponse("error","Backup restore failed", false);
        }
    }

    @PostMapping("/address")
    public APIResponse createBackupSettings(@RequestBody BackupSettingsDto backup){
        ModelMapper mapper = new ModelMapper();
        BackUpSettings backup1 = backupService.addBackupSettings(backup);
        if(backup1 != null){
            return new APIResponse("success","Add backup settings successful", mapper.map(backup1, BackupSettingsDto.class));
        }
        return new APIResponse("failed","Failed to create backup settings", new BackupSettingsDto());
    }

    @GetMapping("/address")
    public APIResponse getBackupSettings(){
        ModelMapper mapper = new ModelMapper();

        BackUpSettings backup1 = backupService.getBackupSettings();
        if (backup1 != null){
            return new APIResponse("success", "Backup settings fetched successfully", mapper.map(backup1, BackupSettingsDto.class));
        }
        return new APIResponse("failed", "Failed to fetch backup files. you may have no backups yet", new BackupSettingsDto());
    }
}
