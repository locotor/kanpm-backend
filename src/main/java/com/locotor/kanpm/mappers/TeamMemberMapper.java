package com.locotor.kanpm.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.locotor.kanpm.model.entities.TeamMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TeamMemberMapper extends BaseMapper<TeamMember> {

    int insertTeamMembers(@Param("teamId") String teamId, @Param("userIds") String[] userIds);

}
