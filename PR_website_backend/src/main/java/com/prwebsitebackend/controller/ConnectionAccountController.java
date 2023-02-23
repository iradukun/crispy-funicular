package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.ConnectionAccountDto;
import com.prwebsitebackend.model.ConnectionAccount;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.ConnectionAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer")
public class ConnectionAccountController {
    private final ConnectionAccountService connectionAccountService;

    ModelMapper mapper = new ModelMapper();

    public ConnectionAccountController(ConnectionAccountService connectionAccountService) {
        this.connectionAccountService = connectionAccountService;
    }

    @PostMapping
    public APIResponse createConnectionAccount(
            @RequestBody ConnectionAccountDto dto
    ) {
        ConnectionAccount account = connectionAccountService.createTeamAccount(dto);
        ConnectionAccountDto res = mapper.map(account, ConnectionAccountDto.class);
        return new APIResponse("Created", "Team account created successfully",res);
    }

    @GetMapping("/{id}")
    public APIResponse getConnectionAccount(
            @PathVariable("id") Long id
    ) {
        ConnectionAccount account = connectionAccountService.getTeamAccount(id);
        ConnectionAccountDto res = mapper.map(account, ConnectionAccountDto.class);
        return new APIResponse("success","Get Customer successful",res);
    }

    @GetMapping("/all")
    public APIResponse getAllConnectionAccounts() {
        List<ConnectionAccount> accounts = connectionAccountService.getAllTeamAccount();
        List<ConnectionAccountDto> res = new ArrayList<>();
        for (ConnectionAccount account : accounts) {
            res.add(mapper.map(account, ConnectionAccountDto.class));
        }
        return new APIResponse("success","Get All Customers",res);
    }
}
