package com.locotor.kanpm.web.controllers;

import java.util.Date;
import java.util.List;

import com.locotor.kanpm.model.entities.TaskStack;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.CreateTaskStackRequest;
import com.locotor.kanpm.model.payloads.UpdateTaskStackRequest;
import com.locotor.kanpm.services.TaskStackService;
import com.locotor.kanpm.web.common.CommonException;
import com.locotor.kanpm.web.common.CommonResponse;
import com.locotor.kanpm.web.security.CurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("insert")
    public Boolean insertTaskStack(@CurrentUser User currentUser, @RequestBody CreateTaskStackRequest insertRequest) {

        String stackName = insertRequest.getStackName();
        String projectId = insertRequest.getProjectId();
        if (projectId.isBlank()) {
            throw new CommonException(ResponseCode.PROJECT_ID_EMPTY);
        }
        if (stackName.isBlank()) {
            List<TaskStack> stackList = taskStackService.getTaskStackListByProjectId(projectId);
            stackName = "未命名任务列表 - " + (stackList.size() + 1);
        } else {
            TaskStack stackTest = taskStackService.getStackByName(stackName, insertRequest.getProjectId());
            if (stackTest != null) {
                throw new CommonException(ResponseCode.TASK_STACK_ALREADY_EXIST);
            }
            if (currentUser == null) {
                throw new CommonException(ResponseCode.USER_NOT_LOGIN);
            }
        }

        TaskStack insertStack = new TaskStack(projectId, stackName);
        insertStack.setCreatorId(currentUser.getId());
        insertStack.setCreateTime(new Date());
        boolean insertResult = taskStackService.save(insertStack);

        if (insertResult) {
            return true;
        }
        throw new CommonException(ResponseCode.FAIL);
    }

    @PutMapping("update")
    public Boolean updateProject(@RequestBody UpdateTaskStackRequest updateRequest) {
        String id = updateRequest.getId();
        if (id.isBlank()) {
            throw new CommonException(ResponseCode.TASK_STACK_ID_EMPTY);
        }

        TaskStack stackNameTest = taskStackService.getStackByName(updateRequest.getStackName(),
                updateRequest.getProjectId());
        if (stackNameTest != null) {
            throw new CommonException(ResponseCode.TASK_STACK_ALREADY_EXIST);
        }

        TaskStack updateStack = taskStackService.getById(id);
        if (!updateRequest.getStackName().isBlank()) {
            updateStack.setStackName(updateRequest.getStackName());
        }
        if (updateRequest.getAlignment() != null) {
            updateStack.setAlignment(updateRequest.getAlignment());
        }
        if (updateRequest.getSortBy() != null) {
            updateStack.setSortBy(updateRequest.getSortBy());
        }
        boolean updateResult = taskStackService.updateById(updateStack);

        if (updateResult) {
            return true;
        } else {
            throw new CommonException(ResponseCode.FAIL);
        }
    }

    @GetMapping("list-by-project-id")
    public List<TaskStack> getTaskStackListByProjectId(String projectId) {
        if (projectId == null || projectId.isBlank()) {
            throw new CommonException(ResponseCode.PROJECT_ID_EMPTY);
        }
        List<TaskStack> stackList = taskStackService.getTaskStackListByProjectId(projectId);
        return stackList;
    }

}
