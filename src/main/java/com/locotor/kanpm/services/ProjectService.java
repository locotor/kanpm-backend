package com.locotor.kanpm.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.locotor.kanpm.mappers.ProjectMapper;
import com.locotor.kanpm.model.entities.Project;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProjectService extends ServiceImpl<ProjectMapper, Project> {

    public Project getProjectByName(String projectName, String teamId) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("project_name", projectName);
        condition.put("team_id", teamId);
        wrapper.allEq(condition);
        return getOne(wrapper);
    }

    public List<Project> getProjectListByTeamId(String teamId) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("team_id", teamId);
        return list(wrapper);
    }

    public boolean archiveProject(String id) {
        UpdateWrapper<Project> wrapper = new UpdateWrapper<>();
        wrapper.set("is_archived", 1).eq("id", id);
        return update(wrapper);
    }

}