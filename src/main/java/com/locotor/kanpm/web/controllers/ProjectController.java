package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.Project;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.AddProjectRequest;
import com.locotor.kanpm.model.payloads.ResponseData;
import com.locotor.kanpm.model.payloads.UpdateProjectRequest;
import com.locotor.kanpm.services.ProjectService;
import com.locotor.kanpm.web.security.CurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("getProjectById")
    public ResponseData getProjectById(String id) {
        Project project = projectService.getById(id);
        if (project == null) {
            return ResponseData.build(ResponseCode.PROJECT_NOT_EXIST);
        }
        return ResponseData.ok(project);
    }

    @GetMapping("getProjectListByTeamId")
    public ResponseData getProjectListByTeamId(String teamId) {
        List<Project> projectList = projectService.getProjectListByTeamId(teamId);
        return ResponseData.ok(projectList);
    }

    @GetMapping("verifyProjectName")
    public ResponseData verifyProjectName(String projectName, String teamId) {
        if (projectName.isBlank()) {
            return ResponseData.build(ResponseCode.PROJECT_EMPTY);
        }
        Project projectTest = projectService.getProjectByName(projectName, teamId);
        if (projectTest != null) {
            return ResponseData.ok(false);
        }
        return ResponseData.ok(true);
    }

    @PostMapping("addProject")
    public ResponseData postMethodName(@CurrentUser User currentUser,
            @RequestBody AddProjectRequest request) {
        Project projectTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectTest != null) {
            return ResponseData.build(ResponseCode.PROJECT_ALREADY_EXIST);
        }

        if (currentUser == null) {
            return ResponseData.build(ResponseCode.USER_NOT_LOGIN);
        }

        String currentUserId = currentUser.getId();
        Project project = new Project(request.getProjectName(), request.getTeamId());
        project.setOwnerId(currentUserId);
        project.setCreatorId(currentUserId);
        project.setIsArchived(false);
        project.setDescription(request.getDescription());
        project.setCreateTime(new Date());
        boolean insertResult = projectService.save(project);

        if (insertResult) {
            return ResponseData.ok(true);
        }
        return ResponseData.build(ResponseCode.FAIL);
    }

    @PutMapping("updateProject")
    public ResponseData updateProject(@RequestBody UpdateProjectRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseData.build(ResponseCode.PROJECT_ID_EMPTY);
        }

        Project projectNameTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectNameTest != null) {
            return ResponseData.build(ResponseCode.PROJECT_ALREADY_EXIST);
        }

        Project project = new Project(request.getId(), request.getTeamId());
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setOwnerId(request.getOwnerId());
        boolean updateResult = projectService.updateById(project);

        if (updateResult) {
            return ResponseData.ok(true);
        } else {
            return ResponseData.build(ResponseCode.FAIL);
        }
    }

    @PutMapping("archiveProject")
    public ResponseData archiveProject(@RequestBody UpdateProjectRequest request) {
        String id = request.getId();
        if (id.isBlank()) {
            return ResponseData.build(ResponseCode.PROJECT_ID_EMPTY);
        }

        boolean updateResult = projectService.archiveProject(id);
        if (updateResult) {
            return ResponseData.ok(true);
        } else {
            return ResponseData.build(ResponseCode.FAIL);
        }
    }

}