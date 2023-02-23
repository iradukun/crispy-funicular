package com.prwebsitebackend.cronJobs;

import com.prwebsitebackend.model.Backup;
import com.prwebsitebackend.repository.BackupRepository;
import com.smattme.MysqlExportService;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class BackUpCronJob {
    private final Environment environment;
    private final JavaMailSender javaMailSender;
    private final BackupRepository backupRepository;

    public BackUpCronJob(Environment environment, JavaMailSender javaMailSender, BackupRepository backupRepository) {
        this.environment = environment;
        this.javaMailSender = javaMailSender;
        this.backupRepository = backupRepository;
    }

    @Scheduled(cron = "0 0 0/6 1/1 * ?")
    public void backupCron()
            throws IOException, SQLException, ClassNotFoundException, MessagingException {
        String dbUsername = environment.getProperty("spring.datasource.username");
        String dbPassword = environment.getProperty("spring.datasource.password");

        String SourceFile = System.getProperty("user.home") + "/backup";

        //Set db properties
        Properties properties = new Properties();
        properties.setProperty(MysqlExportService.DB_NAME, "prtestdb");
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
        message.setRecipients(MimeMessage.RecipientType.TO, "mpanoc6@gmail.com");
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
            System.out.println(file.createNewFile());
        }
        Backup backup = new Backup(fileName, file.getPath());
        backupRepository.save(backup);
    }
}
