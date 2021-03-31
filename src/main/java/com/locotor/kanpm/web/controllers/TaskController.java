package com.locotor.kanpm.web.controllers;

import java.util.Date;
import java.util.List;

import com.locotor.kanpm.model.entities.Task;
import com.locotor.kanpm.model.entities.User;
import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.CreateTaskRequest;
import com.locotor.kanpm.services.TaskService;
import com.locotor.kanpm.web.common.CommonException;
import com.locotor.kanpm.web.common.CommonResponse;
import com.locotor.kanpm.web.security.CurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonResponse
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public Task createTask(@CurrentUser User currentUser, @RequestBody CreateTaskRequest createRequest) {
        String description = createRequest.getDescription();
        String stackId = createRequest.getStackId();
        // TODO 其他任务信息
        if (stackId.isBlank()) {
            throw new CommonException(ResponseCode.TASK_STACK_ID_EMPTY);
        }
        if (description.isBlank()) {
            throw new CommonException(ResponseCode.TASK_EMPTY);
        }
        Task insertTask = new Task(stackId, description);
        insertTask.setCreatorId(currentUser.getId());
        insertTask.setCreateTime(new Date());
        boolean insertResult = taskService.save(insertTask);

        if (insertResult) {
            return insertTask;
        }
        throw new CommonException(ResponseCode.FAIL);
    }

    @GetMapping()
    public List<Task> getTaskListByStackId(String stackId) {
        if (stackId == null || stackId.isBlank()) {
            throw new CommonException(ResponseCode.TASK_STACK_ID_EMPTY);
        }
        List<Task> stackList = taskService.getTaskListByStackId(stackId);
        return stackList;
    }

}