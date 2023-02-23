package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.dtos.SignUpDTO;
import com.hcm.backend_service.v2.enums.EGender;
import com.hcm.backend_service.v2.enums.EUserStatus;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.fileHandling.File;
import com.hcm.backend_service.v2.models.Group;
import com.hcm.backend_service.v2.models.GroupAdmin;
import com.hcm.backend_service.v2.models.Role;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.GroupAdminRepositoryI;
import com.hcm.backend_service.v2.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupAdminService {

    @Autowired
    GroupAdminRepositoryI groupAdminRepository;
    @Autowired
    GroupService groupService;
    @Autowired
    RoleService roleService;
    @Qualifier("bCryptPasswordEncoder")
    @Autowired
    BCryptPasswordEncoder encoder;
    public List<User> getAllGroupAdmin() {
        List<User> groupAdmins = (List<User>) groupAdminRepository.findAll();
        return groupAdmins;
    }

    @Transactional
    public User createGroupAdmin(SignUpDTO groupAdmin, UUID groupId) {
        groupAdmin.setPassword(encoder.encode(groupAdmin.getPassword()));
        Group group = groupService.getGroupById(groupId);
        Role role = roleService.findRoleByName(ERole.GROUP_ADMIN);
        GroupAdmin newGroupAdmin =
                new GroupAdmin(groupAdmin.getEmail(),groupAdmin.getMobile(), EGender.valueOf(groupAdmin.getGender()), groupAdmin.getFullName(), EUserStatus.PENDING, null, groupAdmin.getPassword(), groupAdmin.getConfirmPassword());
        newGroupAdmin.setRole(role);
        newGroupAdmin.setGroup(group);

        return groupAdminRepository.save(newGroupAdmin);
    }

    public User getGroupAdminById(UUID user_id) {
        Optional<User> groupAdmin =
                groupAdminRepository.findById(user_id);
        if(!groupAdmin.isPresent()) {
            throw new ElementNotFoundException("group admin not found");
        }
        return groupAdmin.get();
    }
}
