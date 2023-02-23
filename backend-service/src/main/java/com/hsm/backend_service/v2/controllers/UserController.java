package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.dtos.SignUpDTO;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.fileHandling.File;
import com.hcm.backend_service.v2.fileHandling.FileStorageService;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.payload.ApiResponse;
import com.hcm.backend_service.v2.repositories.IRoleRepository;
import com.hcm.backend_service.v2.security.JwtTokenProvider;
import com.hcm.backend_service.v2.services.IFileService;
import com.hcm.backend_service.v2.services.IUserService;
import com.hcm.backend_service.v2.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v2/users")
public class UserController {

    private final IUserService userService;
    private static final ModelMapper modelMapper = new ModelMapper();
    private final IRoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileStorageService fileStorageService;
    private final IFileService fileService;

    @Value("${uploads.directory.user_profiles}")
    private String directory;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") IUserService userService, IRoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider,
                          FileStorageService fileStorageService, IFileService fileService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.fileService = fileService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping(path = "/current-user")
    public ResponseEntity<ApiResponse> currentlyLoggedInUser(){
        return ResponseEntity.ok(new ApiResponse(true,  userService.getLoggedInUser()));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @GetMapping(path="/paginated")
    public Page<User> getAllUsers(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                  @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return userService.getAll(pageable);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> register(@RequestBody SignUpDTO dto){
        User user = new User();

        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        Role role = roleRepository.findByRole(ERole.valueOf(dto.getRole()));
        if(role == null) {
            role = roleRepository.findByRole(ERole.PATIENT);
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setGender(EGender.valueOf(dto.getGender()));
        user.setFullName(dto.getFullName());
        user.setPassword(encodedPassword);
        user.setRole(role);

        User entity = this.userService.create(user);

        return ResponseEntity.ok(new ApiResponse(true, entity));
    }

    @PutMapping(path="/{id}/upload-profile")
    public ResponseEntity<ApiResponse> uploadProfileImage(
            @PathVariable(value = "id") UUID id,
            @RequestParam("file") MultipartFile document
    ) {
        this.userService.getById(id);
        File file = this.fileService.create(document, directory);

        User updated = this.userService.changeProfileImage(id, file);

        return ResponseEntity.ok(new ApiResponse(true, "File saved successfully", updated));

    }

    @GetMapping("/load-file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> loadProfileImage(@PathVariable String filename) {

        Resource file = this.fileStorageService.load(directory, filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    //get hospital users
    @GetMapping("/hospital/{hospitalId}")
    public List<User> getHospitalUsers(@PathVariable("hospitalId") UUID hospitalId) {
        return userService.getHospitalUsers(hospitalId);
    }


    private User convertDTO(SignUpDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}