package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.AdminListDto;
import com.prwebsitebackend.model.AdminList;
import com.prwebsitebackend.repository.AdminListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminListService {
    @Autowired
    AdminListRepository adminListRepository;

    public AdminList createAdminList(AdminListDto dto) {
        AdminList cl = new AdminList(dto.isTurn(), dto.getCompanyName(), dto.getNumberOfPeople(), dto.getPublicRelations(),
                dto.getSalesperson(), dto.getContractAmount(), dto.getResult(), dto.getBusinessName());
        AdminList saved = adminListRepository.save(cl);
        return saved;
    }

    public AdminList createAdminList(AdminList dto) {
        AdminList cl = new AdminList(dto.isTurn(), dto.getCompanyName(), dto.getNumberOfPeople(), dto.getPublicRelations(),
                dto.getSalesperson(), dto.getContractAmount(), dto.getResult(), dto.getBusinessName());
        AdminList saved = adminListRepository.save(cl);
        return saved;
    }

    public List<AdminList> getAdminLists() {
        return adminListRepository.findAll();
    }

    public AdminList getAdminListById(Long id) {
        return adminListRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        adminListRepository.deleteById(id);
    }
}
