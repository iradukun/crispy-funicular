package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.ManagementPermissionsDto;
import com.prwebsitebackend.dto.TeamDto;
import com.prwebsitebackend.model.ManagementPermissions;
import com.prwebsitebackend.model.StatisticalForm;
import com.prwebsitebackend.model.Team;
import com.prwebsitebackend.model.User;
import com.prwebsitebackend.repository.ManagementPermissionsRepository;
import com.prwebsitebackend.repository.TeamRepository;
import com.prwebsitebackend.utils.LoggedInUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final LoggedInUser user;
    ModelMapper mapper = new ModelMapper();

    public TeamService(TeamRepository teamRepository, LoggedInUser user) {
        this.teamRepository = teamRepository;
        this.user = user;
//        TypeMap<TeamDto, Team> typeMap = mapper.createTypeMap(TeamDto.class, Team.class);
//        typeMap.addMappings()
    }
    public Team createTeam(TeamDto team) {
        User user = this.user.getLoggedInUser();

        Team teamEntity = new Team();
        teamEntity.setName(team.getName());
        teamEntity.setPublicRelations(team.getPublicRelations());
        teamEntity.setPublicRelationsForm(team.getPublicRelationsForm());
        teamEntity.setAccountActivityForm(team.getAccountActivityForm());
        teamEntity.setTeamLeader(team.getTeamLeader());
        //teamEntity.setUser(user);
        return teamRepository.save(teamEntity);
    }
    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElse(null);
    }
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

}
