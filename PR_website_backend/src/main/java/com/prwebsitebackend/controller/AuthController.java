package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.*;
import com.prwebsitebackend.model.LoginLogs;
import com.prwebsitebackend.model.ManagementPermissions;
import com.prwebsitebackend.model.User;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.payload.AuthPayload;
import com.prwebsitebackend.service.AuthService;
import com.prwebsitebackend.utils.HttpUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder encoder;

    public AuthController(AuthService authService, PasswordEncoder encoder) {
        this.authService = authService;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    @ResponseBody
    public AuthPayload login(
            @RequestBody LoginDto dto, HttpServletRequest request
    ) {
        String ip = HttpUtils.getRequestIP(request);
        // String browser = HttpUtils.getBrowserType(request);
        String browser = request.getHeader("User-Agent");
        return authService.login(dto, ip, browser);
    }

    @PostMapping("/register")
    public AuthPayload register(
            @RequestBody SignupDto dto
    ) {
        return authService.register(dto);
    }

    @PutMapping("/change-password/{userId}")
    public AuthPayload changePassword(
            @RequestBody ChangePasswordDto data, @PathVariable String userId
    ) {
        return getAuthPayload(data, authService.getUserById(Long.parseLong(userId)));
    }

    private AuthPayload getAuthPayload(@RequestBody ChangePasswordDto data, User userById) {
        User user = userById;
        if ((encoder.matches(data.getOldpassword(), user.getPassword())) &&
                data.getNewpassword().equals(data.getNewpasswordConfirm())) {

            String encodedPass = encoder.encode(data.getNewpassword());
            user.setPassword(encodedPass);
            return authService.register(user);
        }
        return null;
    }

    @PutMapping("/reset-password/{user_id}")
    public AuthPayload resetPassword(
            @RequestBody ChangePasswordDto data, @PathVariable("user_id") Long userid
    ) {
        return getAuthPayload(data, authService.getUserById(userid));
    }

    @PutMapping("reset-user-password/{user_id}")
    public AuthPayload resetUserPassword(
            @RequestBody ChangePasswordDto data, @PathVariable("user_id") Long userid
    ) {
        User user = authService.getUserById(userid);
        if (data.getNewpassword().equals(data.getNewpasswordConfirm())) {
            String encodedPass = encoder.encode(data.getNewpassword());
            user.setPassword(encodedPass);
            return authService.register(user);
        }
        return new AuthPayload();
    }

    @DeleteMapping("/delete-account/{user_id}")
    public String deleteAccount(@PathVariable("user_id") Long userid) {
        try {
            authService.deleteAccount(userid);
            return "User account was successfully deleted";
        } catch (Exception e) {
            return "Error deleting user " + e.getMessage();
        }
    }

    @DeleteMapping("/deactivate-account/{user_id}")
    public String deactivateAccount(@PathVariable("user_id") Long userid) {
        try {
            boolean deActivate = authService.deleteActivateAccount(userid);
            if (deActivate) {
                return "User account was successfully deactivated";
            }
            return "User account was successfully deactivated";
        } catch (Exception e) {
            return "Error deactivating account " + e.getMessage();
        }
    }

    @GetMapping("/login-logs")
    public List<LoginLogs> getLoginLogs() {
        // System.out.println("TeBro: '" + request.getHeader("User-Agent") + "'");
        return authService.getLoginLogs();
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        ModelMapper mapper =new ModelMapper();
        TypeToken<List<UserDto>> token = new TypeToken<>(){};

        return mapper.map(authService.getAllUsers(),token.getType());
    }

    @PutMapping("/assign-team/{user_id}")
    public APIResponse assignTeam(@RequestBody AssignTeamDto assignTeamDto, @PathVariable("user_id") Long userid) {
        ModelMapper mapper = new ModelMapper();
        TypeToken<Set<TeamDto>> teams = new TypeToken<>(){};
        User user = authService.assignTeam(assignTeamDto.getTeamIds(), userid);
        if(user != null){
            return new APIResponse("success","set user teams successful",mapper.map(user.getTeams(), teams.getType()));
        }
        return new APIResponse("error","set user teams failed",null);
    }

    @PutMapping("/assign-bulletin/{user_id}")
    public APIResponse assignBulletin(@RequestBody AssignBulletinDto assignBulletinDto, @PathVariable("user_id") Long userid) {
        ModelMapper mapper = new ModelMapper();
        TypeToken<Set<UserBulletin>> bulletins = new TypeToken<>(){};
        User user = authService.assignBulletin(assignBulletinDto.getBulletinIds(), userid);
        if(user != null){
            return new APIResponse("success","set user bulletins successful",mapper.map(user.getBulletins(), bulletins.getType()));
        }
        return new APIResponse("error","set user bulletins failed",null);
    }

    @GetMapping("/lastLogin/{userId}")
    public String findLastLogin(@PathVariable Long userId) {
        return authService.getLastLogin(userId);
    }

    @GetMapping("/profile/{userId}")
    public APIResponse getUserProfile(@PathVariable Long userId) {
        ModelMapper mapper = new ModelMapper();
        UserDto val = mapper.map(authService.getUserById(userId), UserDto.class);
        return new APIResponse("success","User Profile Fetched SuccessFully",val);
    }

    @PutMapping("/update-permissions/{userId}")
    public APIResponse updatePermissions(@RequestBody ManagementPermissionsDto permissions , @PathVariable String userId) {
        ModelMapper mapper = new ModelMapper();
        ManagementPermissions permits = authService.updatePermissions(Long.parseLong(userId),mapper.map(permissions, ManagementPermissions.class));
        if (permits!=null) {
            return new APIResponse("success","User Permissions Updated SuccessFully",mapper.map(permits, ManagementPermissionsDto.class));
        }
        return new APIResponse("error","User Permissions Updated Failed",null);
    }

    @GetMapping("/permissions/{userId}")
    public APIResponse getPermissions(@PathVariable String userId) {
        ModelMapper mapper = new ModelMapper();
        ManagementPermissions permits = authService.getUserPermissions(Long.parseLong(userId));
        if (permits!=null) {
            return new APIResponse("success","User Permissions Fetched SuccessFully",mapper.map(permits, ManagementPermissionsDto.class));
        }
        return new APIResponse("error","User Permissions Fetched Failed",null);
    }

}
