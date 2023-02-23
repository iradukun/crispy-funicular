package com.hcm.backend_service.v2.services;

import com.hcm.backend_service.v2.exceptions.BadRequestException;
import com.hcm.backend_service.v2.exceptions.ElementNotFoundException;
import com.hcm.backend_service.v2.models.Group;
import com.hcm.backend_service.v2.models.SuperAdmin;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.GroupRepository;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SuperAdminService superAdminService;
    @Autowired
    UserServiceImpl userServiceImpl;
    public Group createGroup(User loggedInUser, Group group) {
        User created_by =
                superAdminService.getSuperAdminById(loggedInUser.getId());
        group.setCreatedBy(created_by.getId());
        group.setGroupName(group.getGroupName());
        group.setGroupEmail(group.getGroupEmail());
        group.setSuperAdmin((SuperAdmin) created_by);
        return groupRepository.save(group);
    }

    public Group editGroup(User loggedInUser, Group group) {
        Group group1 = groupRepository.findById(group.getGroup_id()).get();

        if (group1 == null) {
            throw new ElementNotFoundException("That group does not exists");
        }
        group1.setGroupName(group.getGroupName());
        group1.setGroupEmail(group.getGroupEmail());
        group1.setUpdatedBy(loggedInUser.getId());
        return groupRepository.save(group1);
    }


    public Group getGroupById(UUID groupId) {
        Optional<Group> group =
                groupRepository.findById(groupId);
        if(!group.isPresent()) {
            throw new ElementNotFoundException("group not found");
        }
        return group.get();
    }
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public String deleteGroup(UUID groupId) {
        try{
            groupRepository.deleteById(groupId);
            return "deleted group successfully";
        }catch (Exception e) {
            throw new BadRequestException("unable to delete group");
        }
    }
    public Long getAllGroupsRegistered() {
        return groupRepository.countAllGroups();
    }
}
