package com.locotor.kanpm.services;

import com.locotor.kanpm.model.entities.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void testTeamSelectById(){
        System.out.println("---- select team by userId 2a8327f5-cfae-11ea-a58a-00155d6db02a ----");
        Team team = teamService.getById("2a8327f5-cfae-11ea-a58a-00155d6db02a");
        System.out.println(team);
    }

    @Test
    public void testTeamSelect(){
        System.out.println("---- select team by userId 38103886-1d49-4cb7-bd68-ee09be00f9f0 ----");
        List<Team> teamList = teamService.getTeamListByMemberId("38103886-1d49-4cb7-bd68-ee09be00f9f0");
        teamList.forEach(System.out::println);
    }

}
