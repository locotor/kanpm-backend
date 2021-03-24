package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.Team;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.AddTeamRequest;
import com.locotor.kanpm.model.payloads.ResponseData;
import com.locotor.kanpm.model.payloads.UpdateTeamRequest;
import com.locotor.kanpm.services.TeamService;
import com.locotor.kanpm.web.security.CurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("getTeam")
    public ResponseData getTeam(String id) {
        Team team = teamService.getById(id);
        if (team != null) {
            return ResponseData.ok(team);
        }
        return ResponseData.build(ResponseCode.TEAM_NOT_EXIST);
    }

    @GetMapping("/verifyTeamName")
    public ResponseData verifyTeamName(String teamName) {
        if (teamName.isBlank()) {
            return ResponseData.build(ResponseCode.TEAM_EMPTY);
        }
        Team teamTest = teamService.getTeamByName(teamName);
        if (teamTest != null) {
            return ResponseData.ok(false);
        }
        return ResponseData.ok(true);
    }

    @GetMapping("getTeamListByMemberId")
    public ResponseData getTeamListByMemberId(String memberId) {
        List<Team> teamList = teamService.getTeamListByMemberId(memberId);
        return ResponseData.ok(teamList);
    }

    @PostMapping("addTeam")
    public ResponseData addTeam(@CurrentUser User currentUser, @RequestBody AddTeamRequest request) {
        Team teamTest = teamService.getTeamByName(request.getTeamName());
        if (teamTest != null) {
            return ResponseData.build(ResponseCode.TEAM_ALREADY_EXIST);
        }

        if (currentUser == null) {
            return ResponseData.build(ResponseCode.USER_NOT_LOGIN);
        }

        String currentUserId = currentUser.getId();
        Team team = new Team(request.getTeamName(), currentUser.getId(), request.getDescription());
        team.setCreatorId(currentUserId);
        boolean insertResult = teamService.save(team);

        if (insertResult) {
            String insertTeamId = team.getId();
            int insertTeamMemberResult = this.teamService.insertTeamMembers(insertTeamId,
                    new String[] { currentUserId });
            if (insertTeamMemberResult > 0) {
                return ResponseData.ok(true);
            } else {
                // TODO rollback team table data
            }
        }
        return ResponseData.build(ResponseCode.FAIL);
    }

    @PutMapping("updateTeam")
    public ResponseData updateTeam(@RequestBody UpdateTeamRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseData.build(ResponseCode.TEAM_NOT_EXIST);
        }

        Team teamTest = teamService.getTeamByName(request.getTeamName());
        if (teamTest != null) {
            return ResponseData.build(ResponseCode.TEAM_ALREADY_EXIST);
        }

        Team team = new Team(teamId, request.getTeamName(), request.getOwnerId(), request.getDescription());
        boolean updateResult = teamService.save(team);

        if (updateResult) {
            return ResponseData.ok(true);
        } else {
            return ResponseData.build(ResponseCode.FAIL);
        }
    }

    @PutMapping("archiveTeam")
    public ResponseData archiveTeam(@RequestBody UpdateTeamRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseData.build(ResponseCode.TEAM_NOT_EXIST);
        }

        boolean updateResult = teamService.archiveTeam(teamId);
        if (updateResult) {
            return ResponseData.ok(true);
        } else {
            return ResponseData.build(ResponseCode.FAIL);
        }
    }

}