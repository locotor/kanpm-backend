package com.locotor.kanpm.web.controllers;

import java.util.List;

import com.locotor.kanpm.model.entities.TaskStack;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.services.TaskStackService;
import com.locotor.kanpm.web.common.CommonException;
import com.locotor.kanpm.web.common.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonResponse
@RequestMapping("/api/task-stack")
public class TaskStackController {

    private TaskStackService taskStackService;

    @Autowired
    public TaskStackController(TaskStackService taskStackService) {
        this.taskStackService = taskStackService;
    }

    @GetMapping("get-task-list-by-project-id")
    public List<TaskStack> getTaskStackListByProjectId(String projectId) {
        if (projectId == null || projectId.isBlank()) {
            throw new CommonException(ResponseCode.PROJECT_ID_EMPTY);
        }
        List<TaskStack> stackList = taskStackService.getTaskStackListByProjectId(projectId);
        return stackList;
    }

}
