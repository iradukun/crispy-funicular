package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.ConnectionAccountDto;
import com.prwebsitebackend.model.ConnectionAccount;
import com.prwebsitebackend.repository.TeamAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionAccountService {
    private final PasswordEncoder passwordEncoder;
    private final TeamAccountRepository teamAccountRepository;

    public ConnectionAccountService(PasswordEncoder passwordEncoder, TeamAccountRepository teamAccountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.teamAccountRepository = teamAccountRepository;
    }

    public ConnectionAccount createTeamAccount(ConnectionAccountDto dto) {
        if(dto.getPassword().equals(dto.getVerifyPassword())){
            String password = passwordEncoder.encode(dto.getPassword());
            ConnectionAccount connectionAccount =
                    new ConnectionAccount(dto.getName(), dto.getId(), password, dto.getAuthority());
            return teamAccountRepository.save(connectionAccount);
        }
        return null;
    }

    public ConnectionAccount getTeamAccount(Long id) {
        return teamAccountRepository.findById(id).orElse(null);
    }

    public List<ConnectionAccount> getAllTeamAccount() {
        return teamAccountRepository.findAll();
    }
}
