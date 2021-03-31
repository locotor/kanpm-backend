package com.locotor.kanpm.services;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.locotor.kanpm.mappers.TaskMapper;
import com.locotor.kanpm.model.entities.Task;

import org.springframework.stereotype.Service;

@Service
public class TaskService extends ServiceImpl<TaskMapper, Task> {

    public List<Task> getTaskListByStackId(String projectId) {
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        wrapper.eq("stack_id", projectId);
        return list(wrapper);
    }

}
