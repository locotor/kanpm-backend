package com.locotor.kanpm.web.controllers;

import com.locotor.kanpm.model.entities.Project;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.AddProjectRequest;
import com.locotor.kanpm.model.payloads.UpdateProjectRequest;
import com.locotor.kanpm.services.ProjectService;
import com.locotor.kanpm.web.common.CommonException;
import com.locotor.kanpm.web.common.CommonResponse;
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
@CommonResponse
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("get")
    public Project getProjectById(String id) {
        Project project = projectService.getById(id);
        if (project == null) {
            throw new CommonException(ResponseCode.PROJECT_NOT_EXIST);
        }
        return project;
    }

    @GetMapping("list-by-team-id")
    public List<Project> getProjectListByTeamId(String teamId) {
        List<Project> projectList = projectService.getProjectListByTeamId(teamId);
        return projectList;
    }

    @GetMapping("verifyProjectName")
    public Boolean verifyProjectName(String projectName, String teamId) {
        if (projectName.isBlank()) {
            throw new CommonException(ResponseCode.PROJECT_EMPTY);
        }
        Project projectTest = projectService.getProjectByName(projectName, teamId);
        if (projectTest != null) {
            return false;
        }
        return true;
    }

    @PostMapping("insert")
    public Boolean postMethodName(@CurrentUser User currentUser,
            @RequestBody AddProjectRequest request) {
        Project projectTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectTest != null) {
            throw new CommonException(ResponseCode.PROJECT_ALREADY_EXIST);
        }

        if (currentUser == null) {
            throw new CommonException(ResponseCode.USER_NOT_LOGIN);
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
            return true;
        }
        throw new CommonException(ResponseCode.FAIL);
    }

    @PutMapping("update")
    public Boolean updateProject(@RequestBody UpdateProjectRequest request) {
        String teamId = request.getId();
        if (teamId.isBlank()) {
            throw new CommonException(ResponseCode.PROJECT_ID_EMPTY);
        }

        Project projectNameTest = projectService.getProjectByName(request.getProjectName(), request.getTeamId());
        if (projectNameTest != null) {
            throw new CommonException(ResponseCode.PROJECT_ALREADY_EXIST);
        }

        Project project = new Project(request.getId(), request.getTeamId());
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setOwnerId(request.getOwnerId());
        boolean updateResult = projectService.updateById(project);

        if (updateResult) {
            return true;
        } else {
            throw new CommonException(ResponseCode.FAIL);
        }
    }

    @PutMapping("archive")
    public Boolean archiveProject(@RequestBody UpdateProjectRequest request) {
        String id = request.getId();
        if (id.isBlank()) {
            throw new CommonException(ResponseCode.PROJECT_ID_EMPTY);
        }

        boolean updateResult = projectService.archiveProject(id);
        if (updateResult) {
            return true;
        } else {
            throw new CommonException(ResponseCode.FAIL);
        }
    }

}