package com.locotor.kanpm.services;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.locotor.kanpm.mappers.TaskStackMapper;
import com.locotor.kanpm.model.entities.TaskStack;
import org.springframework.stereotype.Service;

@Service
public class TaskStackService extends ServiceImpl<TaskStackMapper, TaskStack> {

    public List<TaskStack> getTaskStackListByProjectId(String projectId) {
        QueryWrapper<TaskStack> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        return list(wrapper);
    }

}
