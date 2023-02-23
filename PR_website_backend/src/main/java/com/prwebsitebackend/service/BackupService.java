package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.BackupSettingsDto;
import com.prwebsitebackend.model.BackUpSettings;
import com.prwebsitebackend.model.Backup;
import com.prwebsitebackend.model.User;
import com.prwebsitebackend.repository.BackUpSettingsRepository;
import com.prwebsitebackend.repository.BackupRepository;
import com.prwebsitebackend.utils.LoggedInUser;
import com.smattme.MysqlExportService;
import com.smattme.MysqlImportService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Service
public class BackupService {

    private final JavaMailSender javaMailSender;
    private final BackupRepository backupRepository;
    private final BackUpSettingsRepository backUpSettingsRepository;
    private final LoggedInUser loggedInUser;

    public BackupService(JavaMailSender javaMailSender,BackupRepository backupRepository, BackUpSettingsRepository backUpSettingsRepository, LoggedInUser loggedInUser) {
        this.javaMailSender = javaMailSender;
        this.backupRepository = backupRepository;
        this.backUpSettingsRepository = backUpSettingsRepository;
        this.loggedInUser = loggedInUser;
    }
    /**TODO
     * MAKE A CRONJOB TO RUN THIS METHOD EVERY 6 HOURS
     **/
    public boolean backup(String dbUsername, String dbPassword, String dbName)
            throws IOException, SQLException, ClassNotFoundException, MessagingException {

        BackUpSettings settings= getBackupSettings();
        if (settings == null) {
            return false;
        }

        String SourceFile = System.getProperty("user.home") + "/backup";

        //Set db properties
        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.DB_NAME, dbName);
        properties.setProperty(MysqlExportService.DB_USERNAME, dbUsername);
        properties.setProperty(MysqlExportService.DB_PASSWORD, dbPassword);
        properties.setProperty(MysqlExportService.JDBC_CONNECTION_STRING, "jdbc:mysql://database-1.ccardft9heie.us-east-1.rds.amazonaws.com/prtestdb?useSSL=false&allowPublicKeyRetrieval=true");

        properties.setProperty(MysqlExportService.TEMP_DIR, new File(SourceFile).getPath());
        properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");

        MysqlExportService mysqlExportService = new MysqlExportService(properties);
        mysqlExportService.export();
        File file = mysqlExportService.getGeneratedZipFile();
        String fileName = file.getName();

        // Emailing the Zip backup file to User
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject("System Backup File");
        message.setRecipients(MimeMessage.RecipientType.TO, settings.getAddress());
        message.setText("This backup was done automatically by the system at your request. Please do not reply to this email.");

        // Create a MimeBodyPart object to hold the attachment
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();

        // Set the attachment file and MIME type
        FileDataSource fileDataSource = new FileDataSource(file.getAbsolutePath());
        attachmentBodyPart.setDataHandler(new DataHandler(fileDataSource));
        attachmentBodyPart.setFileName(fileDataSource.getName());

        // Create a Multipart object to hold the text and attachment
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachmentBodyPart);

        // Set the Multipart object as the message's content
        message.setContent(multipart);

        // Send Email
        javaMailSender.send(message);

        if (!file.exists()) {
            return false;
        }
        Backup backup = new Backup(fileName, file.getPath());
        backupRepository.save(backup);
        return true;
    }

    public List<Backup> getAllBackup() {
        return backupRepository.findAll();
    }

    public boolean restore(Long backupId, String dbUsername, String dbPassword, String dbName) throws IOException,SQLException, ClassNotFoundException {
        Backup backup = backupRepository.findById(backupId).orElse(null);
        String sql = null;
        if (backup != null) {
            sql = new String(Files.readAllBytes(Paths.get(backup.getFileUrl())));
        }
        return MysqlImportService.builder()
                .setDatabase(dbName)
                .setSqlString(sql)
                .setJdbcConnString("jdbc:mysql://database-1.ccardft9heie.us-east-1.rds.amazonaws.com/prtestdb?useSSL=false&allowPublicKeyRetrieval=true")
                .setUsername(dbUsername)
                .setPassword(dbPassword)
//                .setDeleteExisting(true)
//                .setDropExisting(true)
                .importDatabase();
    }

    public BackUpSettings addBackupSettings(BackupSettingsDto address) {
        User user = loggedInUser.getLoggedInUser();

        if (!user.getRole().equals("SUPER_ADMIN")) {
            return null;
        }

        BackUpSettings backup1 = backUpSettingsRepository.findByUserId(user.getUserId());
        if(backup1 != null){
            backup1.setAddress(address.getAddress());
            backup1.setTime(address.getTime());
            return backUpSettingsRepository.save(backup1);
        }
        BackUpSettings backup = new BackUpSettings();
        backup.setAddress(address.getAddress());
        backup.setTime(address.getTime());
        backup.setUser(user);

        return backUpSettingsRepository.save(backup);
    }

    public BackUpSettings getBackupSettings(){
        User user = loggedInUser.getLoggedInUser();
        if(!(user.getRole().equals("SUPER_ADMIN"))){
            return null;
        }
        return backUpSettingsRepository.findByUserId(user.getUserId());
    }
}
