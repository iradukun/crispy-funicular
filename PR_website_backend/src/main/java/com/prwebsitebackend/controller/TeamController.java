package com.prwebsitebackend.controller;

import com.prwebsitebackend.dto.TeamDto;
import com.prwebsitebackend.model.Team;
import com.prwebsitebackend.payload.APIResponse;
import com.prwebsitebackend.service.TeamService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;
    ModelMapper mapper = new ModelMapper();

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createTeam(@RequestBody TeamDto team) {
        //System.out.println(team.getAccountActivityForm());
        Team val=teamService.createTeam(team);
        return ResponseEntity.ok(new APIResponse("success", "Team created successfully",mapper.map(val, TeamDto.class)));
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse> getTeam(@PathVariable("id") Long id) {
        Team val=teamService.getTeam(id);
        return ResponseEntity.ok(new APIResponse("success", "Team fetched successfully",mapper.map(val, TeamDto.class)));
    }

    @GetMapping("/getTeams")
    public ResponseEntity<APIResponse> getAllTeams() {
        TypeToken<List<TeamDto>> token = new TypeToken<>() {};
        List<Team> val=teamService.getTeams();
        if(val!=null){
            return ResponseEntity.ok(new APIResponse("success", "No teams found",mapper.map(val, token.getType())));
        }
        return ResponseEntity.ok(new APIResponse("success", "Team fetched successfully",null));
    }

}
