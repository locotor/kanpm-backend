package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.Project;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.payloads.AddProjectRequest;
import com.locotor.kanpm.model.payloads.ApiResponse;
import com.locotor.kanpm.model.payloads.UpdateProjectRequest;
import com.locotor.kanpm.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends ControllerBase {

    private ProjectService projectService;

    @Autowired
    public  ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("getProjectById")
    public ResponseEntity<ApiResponse> getProjectById(String id) {
        Project project = projectService.getById(id);
        var resp = new ApiResponse(true, "Add project successfully");
        resp.setData(project);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("getProjectListByTeamId")
    public ResponseEntity<ApiResponse> getProjectListByTeamId(String teamId) {
        var teamList = projectService.getProjectListByTeamId(teamId);
        var resp = new ApiResponse(true, "get project list successfully");
        resp.setData(teamList);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("verifyProjectName")
    public ResponseEntity<ApiResponse> verifyProjectName(String projectName, String teamId) {
        if (projectName.isBlank()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "parameter should not be blank"));
        }
        Project projectTest = projectService.getProjectByName(projectName, teamId);
        if (projectTest != null) {
            return ResponseEntity.ok(new ApiResponse(false, "project name is already exist"));
        }
        return ResponseEntity.ok(new ApiResponse(true));
    }

    @PostMapping("addProject")
    public ResponseEntity<ApiResponse> postMethodName(@RequestBody AddProjectRequest request) {
        Project projectTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectTest != null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Project name is already taken!"));
        }

        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "need to signin first!"));
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
            return ResponseEntity.ok(new ApiResponse(true, "Add project successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Add project failed"));
    }

    @PutMapping("updateProject")
    public ResponseEntity<ApiResponse> updateProject(@RequestBody UpdateProjectRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Project id must not be null"));
        }

        Project projectNameTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectNameTest != null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Project name is already taken!"));
        }

        Project project = new Project(request.getId(), request.getTeamId());
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setOwnerId(request.getOwnerId());
        boolean updateResult = projectService.updateById(project);

        if (updateResult) {
            return ResponseEntity.ok(new ApiResponse(true, "update project successfully"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "update project failed"));
        }
    }

    @PutMapping("archiveProject")
    public ResponseEntity<ApiResponse> archiveProject(@RequestBody UpdateProjectRequest request) {
        String id = request.getId();
        if (id.isBlank()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Project id must not be null"));
        }

        boolean updateResult = projectService.archiveProject(id);
        if (updateResult) {
            return ResponseEntity.ok(new ApiResponse(true, "archive project successfully"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "archive project failed"));
        }
    }

}