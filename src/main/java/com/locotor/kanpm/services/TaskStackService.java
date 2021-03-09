package com.locotor.kanpm.services;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.locotor.kanpm.mappers.TaskStackMapper;
import com.locotor.kanpm.model.entities.TaskStack;
import org.springframework.stereotype.Service;

@Service
public class TaskStackService extends ServiceImpl<TaskStackMapper, TaskStack> {
}
