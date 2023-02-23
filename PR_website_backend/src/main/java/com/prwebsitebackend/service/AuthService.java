package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.LoginDto;
import com.prwebsitebackend.dto.SignupDto;
import com.prwebsitebackend.dto.UserDto;
import com.prwebsitebackend.enums.AccountStatus;
import com.prwebsitebackend.model.*;
import com.prwebsitebackend.payload.AuthPayload;
import com.prwebsitebackend.repository.*;
import com.prwebsitebackend.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {


    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final LoginLogsRepository loginLogsRepository;
    private final PasswordEncoder encoder;
    private final TeamRepository teamRepository;
    private final ManagementPermissionsRepository managementPermissionsRepository;
    private final BulletinRepository bulletinRepository;

    public AuthService(JWTUtil jwtUtil, AuthenticationManager authenticationManager, UserRepository userRepository, LoginLogsRepository loginLogsRepository, PasswordEncoder encoder, TeamRepository teamRepository, ManagementPermissionsRepository managementPermissionsRepository, BulletinRepository bulletinRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.loginLogsRepository = loginLogsRepository;
        this.encoder = encoder;
        this.teamRepository = teamRepository;
        this.managementPermissionsRepository = managementPermissionsRepository;
        this.bulletinRepository = bulletinRepository;
    }

    public AuthPayload login(LoginDto dto, String ip, String browser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(dto.getUsername());
        User user =
                userRepository.findByUsername(dto.getUsername());
        AuthPayload authPayload =
                new AuthPayload(token, new UserDto(user.getUserId(), user.getId(),user.getUsername(), user.getRole()));
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        LoginLogs newLog = new LoginLogs(user.getUserId(), user.getUsername(), ip, browser, date);
        loginLogsRepository.save(newLog);
        return authPayload;
    }

    public AuthPayload register(SignupDto dto) {

        if(dto.getPassword().equals(dto.getVerifyPassword())){
            ManagementPermissions permissions = new ManagementPermissions();
            String encodedPass = encoder.encode(dto.getPassword());
            User user = new User(dto.getUsername() ,dto.getId() ,encodedPass, dto.getAuthority());
            if(user.getRole().equals("SUPER_ADMIN")) {
                permissions.setSetPermissions(true);
                permissions.setAccessRights(true);
                permissions.setProbabilitySettingPermission(true);
                user.setManagementPermissions(permissions);
            }else{
                user.setManagementPermissions(permissions);
            }
            User saved = userRepository.save(user);
            String token = jwtUtil.generateToken(saved.getUsername());
            AuthPayload authPayload = new AuthPayload(token, new UserDto(saved.getUserId(), saved.getId(), saved.getUsername(), saved.getRole()));
            return authPayload;
        }
        return null;
    }

    public AuthPayload register(User user) {
        User saved = userRepository.save(user);
        String token =
                jwtUtil.generateToken(saved.getUsername());
        AuthPayload authPayload =
                new AuthPayload(token, new UserDto(saved.getUserId(), saved.getId(),saved.getUsername(), saved.getRole()));
        return authPayload;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteAccount(Long userid) {
        userRepository.deleteById(userid);
    }

    public boolean deleteActivateAccount(Long userid) {
        User existing = userRepository.findById(userid).orElse(null);
        if (existing != null) {
            existing.setAccountStatus(AccountStatus.DEACTIVACTED);
            userRepository.save(existing);
            return true;
        }
        return false;
    }
    public List<LoginLogs> getLoginLogs() {
        return loginLogsRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User assignTeam(Set<Long> teamIds, Long userid) {
        User existing = userRepository.findById(userid).orElse(null);
        Set<Team> teams = new HashSet<>(teamRepository.findAllById(teamIds));
        if (existing != null) {
            boolean teamsAdded = existing.getTeams().addAll(teams);
            if(teamsAdded){
                return userRepository.save(existing);
            }
        }
        return null;
    }

    public User assignBulletin(Set<Long>bulletinIds,Long userid){
        User existingUser = userRepository.findById(userid).orElse(null);
        Set<Bulletin> bulletins = new HashSet<>(bulletinRepository.findAllById(bulletinIds));
        if (existingUser != null) {
            boolean bulletinsAdded = existingUser.getBulletins().addAll(bulletins);
            if(bulletinsAdded) {
                return userRepository.save(existingUser);
            }
        }
        return null;
    }

    public String getLastLogin(Long userId){
       LoginLogs lastLogin = loginLogsRepository.findFirstByUserIdOrderByLoginDateDesc(userId);
       if(lastLogin!=null) {
           return lastLogin.getLoginDate().toString();
       }
       return "No login logs available for this user";
    }

    public ManagementPermissions updatePermissions(Long userId, ManagementPermissions permissions){
        User existing = userRepository.findById(userId).orElse(null);
        if(existing != null){
            ManagementPermissions managementPermissions = existing.getManagementPermissions();
            managementPermissions.setAccessRights(permissions.isAccessRights());
            managementPermissions.setProbabilitySettingPermission(permissions.isProbabilitySettingPermission());
            managementPermissions.setSetPermissions(permissions.isSetPermissions());
            return managementPermissionsRepository.save(managementPermissions);
        }
        return null;
    }

    public ManagementPermissions getUserPermissions(Long userId){
        User user = userRepository.findById(userId).orElse(null);
       return user != null ? user.getManagementPermissions() : null;
    }
}
