package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.Team;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.AddTeamRequest;
import com.locotor.kanpm.model.payloads.ResponseData;
import com.locotor.kanpm.model.payloads.UpdateTeamRequest;
import com.locotor.kanpm.services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController extends ControllerBase {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("getTeam")
    public ResponseEntity<ResponseData> getTeam(String id) {
        Team team = teamService.getById(id);
        if (team != null) {
            return ResponseEntity.ok(ResponseData.ok(team));
        }
        return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.TEAM_NOT_EXIST));
    }

    @GetMapping("/verifyTeamName")
    public ResponseEntity<ResponseData> verifyTeamName(String teamName) {
        if (teamName.isBlank()) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.TEAM_EMPTY));
        }
        Team teamTest = teamService.getTeamByName(teamName);
        if (teamTest != null) {
            return ResponseEntity.ok(ResponseData.ok(false));
        }
        return ResponseEntity.ok(ResponseData.ok(true));
    }

    @GetMapping("getTeamListByMemberId")
    public ResponseEntity<ResponseData> getTeamListByMemberId(String memberId) {
        List<Team> teamList = teamService.getTeamListByMemberId(memberId);
        return ResponseEntity.ok(ResponseData.ok(teamList));
    }

    @PostMapping("addTeam")
    public ResponseEntity<ResponseData> addTeam(@RequestBody AddTeamRequest request) {
        Team teamTest = teamService.getTeamByName(request.getTeamName());
        if (teamTest != null) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.TEAM_ALREADY_EXIST));
        }

        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.USER_NOT_LOGIN));
        }

        String currentUserId = currentUser.getId();
        Team team = new Team(request.getTeamName(), currentUser.getId(), request.getDescription());
        team.setCreatorId(currentUserId);
        boolean insertResult = teamService.save(team);

        if (insertResult) {
            String insertTeamId = team.getId();
            int insertTeamMemberResult = this.teamService.insertTeamMembers(insertTeamId,
                    new String[]{currentUserId});
            if (insertTeamMemberResult > 0) {
                return ResponseEntity.ok(ResponseData.ok(true));
            }else{
                //TODO rollback team table data
            }
        }
        return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.FAIL));
    }

    @PutMapping("updateTeam")
    public ResponseEntity<ResponseData> updateTeam(@RequestBody UpdateTeamRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.TEAM_NOT_EXIST));
        }

        Team teamTest = teamService.getTeamByName(request.getTeamName());
        if (teamTest != null) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.TEAM_ALREADY_EXIST));
        }

        Team team = new Team(teamId, request.getTeamName(), request.getOwnerId(), request.getDescription());
        boolean updateResult = teamService.save(team);

        if (updateResult) {
            return ResponseEntity.ok(ResponseData.ok(true));
        } else {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.FAIL));
        }
    }

    @PutMapping("archiveTeam")
    public ResponseEntity<ResponseData> archiveTeam(@RequestBody UpdateTeamRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.TEAM_NOT_EXIST));
        }

        boolean updateResult = teamService.archiveTeam(teamId);
        if (updateResult) {
            return ResponseEntity.ok(ResponseData.ok(true));
        } else {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.FAIL));
        }
    }

}