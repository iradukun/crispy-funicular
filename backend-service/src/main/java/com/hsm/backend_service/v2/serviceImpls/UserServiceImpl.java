package com.hcm.backend_service.v2.serviceImpls;

import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.exceptions.ResourceNotFoundException;
import com.hcm.backend_service.v2.fileHandling.File;
import com.hcm.backend_service.v2.models.Hospital;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.HospitalRepository;
import com.hcm.backend_service.v2.repositories.IUserRepository;
import com.hcm.backend_service.v2.services.HospitalService;
import com.hcm.backend_service.v2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Qualifier("IUserRepository")
    @Autowired
    private final IUserRepository userRepository;


    @Autowired
    public UserServiceImpl(@Qualifier("IUserRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User getById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id.toString()));
    }

    @Override
    public User create(User user) {
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent())
            throw new BadRequestException(String.format("User with email '%s' already exists", user.getEmail()));

        return this.userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("User", "id", id.toString()));

        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent() && (userOptional.get().getId() != entity.getId()))
            throw new BadRequestException(String.format("User with email '%s' already exists", entity.getEmail()));

        entity.setEmail(user.getEmail());
        entity.setFullName(user.getFullName());
        entity.setMobile(user.getMobile());
        entity.setGender(user.getGender());


        return this.userRepository.save(entity);
    }

    @Override
    public boolean delete(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));

        user.setStatus(EUserStatus.DEACTIVATED);

        this.userRepository.save(user);
        return true;
    }



    @Override
    public List<User> searchUser(String searchKey) {
        return this.userRepository.searchUser(searchKey);
    }

    @Override
    public Page<User> searchUser(Pageable pageable, String searchKey) {
        return this.userRepository.searchUser(pageable, searchKey);
    }

    @Override
    public User getLoggedInUser() {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        }else{
            email = principal.toString();
        }
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", email));
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", email));
    }

    @Override
    public User changeStatus(UUID id, EUserStatus status) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("User", "id", id.toString()));

        entity.setStatus(status);

        return  this.userRepository.save(entity);
    }

    @Override
    public User changeProfileImage(UUID id, File file) {
        User entity = this.userRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("Document", "id", id.toString()));

        entity.setProfileImage(file);
        return  this.userRepository.save(entity);

    }

    @Override
    public User getUserByEmailOrMobile(String email, String mobile) {
        User entity =
                this.userRepository.findByEmailOrMobile(email, mobile).orElseThrow(
                        () -> new ElementNotFoundException("user not found")
                );
        return entity;
    }

    @Override
    public List<User> getHospitalUsers(UUID hospitalId) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);
        if(!hospitalOptional.isPresent()) {
            throw new ElementNotFoundException("hospital not found");
        }
        Hospital hospital = hospitalOptional.get();
        List<User> hospitalUsers = new ArrayList<>();
        hospitalUsers.addAll(hospital.getDoctors());
        hospitalUsers.addAll(hospital.getHospitalAdmins());
        hospitalUsers.addAll(hospital.getAppointmentManagers());
        hospitalUsers.addAll(hospital.getScheduleManagers());
        return hospitalUsers;
    }

    @Override
    public Long getNumberOfUsersRegistered() {
        return userRepository.countAllUsers();
    }
}
