package com.locotor.kanpm.mappers;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.locotor.kanpm.model.entities.Team;

public interface TeamMapper extends BaseMapper<Team> {

    List<Team> getTeamListByUserId(String userId);

}