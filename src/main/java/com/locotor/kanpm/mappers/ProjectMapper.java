package com.locotor.kanpm.mappers;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.locotor.kanpm.model.entities.Project;

public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> getProjectListByTeamId(String teamId);

}