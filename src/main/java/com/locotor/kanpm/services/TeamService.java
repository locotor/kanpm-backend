package com.locotor.kanpm.services;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.locotor.kanpm.mappers.TeamMemberMapper;
import com.locotor.kanpm.model.entities.Team;
import com.locotor.kanpm.mappers.TeamMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends ServiceImpl<TeamMapper, Team> {

    private TeamMemberMapper teamMemberMapper;

    @Autowired
    public TeamService(TeamMemberMapper teamMemberMapper) {
        this.teamMemberMapper = teamMemberMapper;
    }

    public Team getTeamByName(String teamName) {
        QueryWrapper<Team> wrapper = new QueryWrapper<>();
        wrapper.eq("team_name", teamName);
        return getOne(wrapper);
    }

    public boolean archiveTeam(String teamId) {
        UpdateWrapper<Team> wrapper = new UpdateWrapper<>();
        wrapper.set("is_archived", 1).eq("team_id", teamId);
        return update(wrapper);
    }

    public List<Team> getTeamListByMemberId(String memberId) {
        return getBaseMapper().getTeamListByUserId(memberId);
    }

    public int insertTeamMembers(String teamId, String[] userIds) {
        return teamMemberMapper.insertTeamMembers(teamId, userIds);
    }
}