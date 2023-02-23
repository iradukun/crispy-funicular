package com.hcm.backend_service.v2.controllers;

import com.hcm.backend_service.v2.models.Group;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import com.hcm.backend_service.v2.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/groups")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserServiceImpl userServiceImpl;
    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
    @PostMapping
    public Group createGroup(
            @RequestBody Group group)
    {
        User loggedInUser =
                userServiceImpl.getLoggedInUser();
        return groupService.createGroup(loggedInUser, group);
    }
    @GetMapping("/{groupId}")
    public Group getGroupDetails(
            @PathVariable("groupId") UUID groupId
    ) {
        return groupService.getGroupById(groupId);
    }
    @DeleteMapping("/{groupId}")
    public String deleteGroup(
            @PathVariable("groupId") UUID groupId
    ) {
        return groupService.deleteGroup(groupId);

    }
    @PutMapping("/{group_id}")
    public Group editGroup(@RequestBody Group group, @PathVariable UUID group_id) {
        User loggedInUser =
                userServiceImpl.getLoggedInUser();
        group.setGroup_id(group_id);
        return groupService.editGroup(loggedInUser, group);
    }
}
