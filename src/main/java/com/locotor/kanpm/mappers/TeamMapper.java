package com.locotor.kanpm.mappers;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.locotor.kanpm.model.entities.Team;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface TeamMapper extends BaseMapper<Team> {

    List<Team> getTeamListByUserId(String userId);

}