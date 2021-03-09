package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.Project;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.AddProjectRequest;
import com.locotor.kanpm.model.payloads.ResponseData;
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
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends ControllerBase {

    private ProjectService projectService;

    @Autowired
    public  ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("getProjectById")
    public ResponseEntity<ResponseData> getProjectById(String id) {
        Project project = projectService.getById(id);
        if(project == null){
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.PROJECT_NOT_EXIST));
        }
        return ResponseEntity.ok(ResponseData.ok(project));
    }

    @GetMapping("getProjectListByTeamId")
    public ResponseEntity<ResponseData> getProjectListByTeamId(String teamId) {
        List<Project> projectList = projectService.getProjectListByTeamId(teamId);
        return ResponseEntity.ok(ResponseData.ok(projectList));
    }

    @GetMapping("verifyProjectName")
    public ResponseEntity<ResponseData> verifyProjectName(String projectName, String teamId) {
        if (projectName.isBlank()) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.PROJECT_EMPTY));
        }
        Project projectTest = projectService.getProjectByName(projectName, teamId);
        if (projectTest != null) {
            return ResponseEntity.ok(ResponseData.ok(false));
        }
        return ResponseEntity.ok(ResponseData.ok(true));
    }

    @PostMapping("addProject")
    public ResponseEntity<ResponseData> postMethodName(@RequestBody AddProjectRequest request) {
        Project projectTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectTest != null) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.PROJECT_ALREADY_EXIST));
        }

        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.USER_NOT_LOGIN));
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
            return ResponseEntity.ok(ResponseData.ok(true));
        }
        return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.FAIL));
    }

    @PutMapping("updateProject")
    public ResponseEntity<ResponseData> updateProject(@RequestBody UpdateProjectRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.PROJECT_ID_EMPTY));
        }

        Project projectNameTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectNameTest != null) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.PROJECT_ALREADY_EXIST));
        }

        Project project = new Project(request.getId(), request.getTeamId());
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setOwnerId(request.getOwnerId());
        boolean updateResult = projectService.updateById(project);

        if (updateResult) {
            return ResponseEntity.ok(ResponseData.ok(true));
        } else {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.FAIL));
        }
    }

    @PutMapping("archiveProject")
    public ResponseEntity<ResponseData> archiveProject(@RequestBody UpdateProjectRequest request) {
        String id = request.getId();
        if (id.isBlank()) {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.PROJECT_ID_EMPTY));
        }

        boolean updateResult = projectService.archiveProject(id);
        if (updateResult) {
            return ResponseEntity.ok(ResponseData.ok(true));
        } else {
            return ResponseEntity.badRequest().body(ResponseData.build(ResponseCode.FAIL));
        }
    }

}