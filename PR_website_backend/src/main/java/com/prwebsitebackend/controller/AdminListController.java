package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.AdminListDto;
import com.prwebsitebackend.model.AdminList;
import com.prwebsitebackend.service.AdminListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/adminList")
public class AdminListController {
    private final AdminListService adminListService;

    public AdminListController(AdminListService adminListService) {
        this.adminListService = adminListService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String listAllCasterLeaders() {
        return "Looking like nothing goes on here";
    }
//    public List<AdminList> listAllCasterLeaders() {
//        return adminListService.getAdminLists();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addNew(@RequestBody AdminListDto adminList) {
        AdminListDto newCasterLeader = adminList;
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        newCasterLeader.setAppointmentDate(date);
        adminListService.createAdminList(newCasterLeader);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminList findCasterLeaderById(@PathVariable("id") Long id) {
        AdminList adminList = adminListService.getAdminListById(id);
        if (adminList != null) {
            return adminList;
        } else {
            return null;
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCasterLeader(@RequestBody AdminListDto new_info, @PathVariable("id") Long id) {
        AdminList adminList = adminListService.getAdminListById(id);
        adminList.setTurn(new_info.isTurn());
        adminList.setCompanyName(new_info.getCompanyName());
        adminList.setNumberOfPeople(new_info.getNumberOfPeople());
        adminList.setResult(new_info.getResult());
        adminList.setSalesperson(new_info.getSalesperson());
        adminList.setBusinessName(new_info.getBusinessName());
        adminList.setContractAmount(new_info.getContractAmount());
        adminList.setPublicRelations(new_info.getPublicRelations());
        adminListService.createAdminList(adminList);
    }

    @DeleteMapping("{id}")
    public void deleteCasterLeader(@PathVariable("id") Long id) {
        adminListService.deleteUserById(id);
    }
}
