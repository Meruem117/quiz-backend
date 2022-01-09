package com.niit.quiz.service;

import com.niit.quiz.model.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TeamServiceTest {
    @Resource
    private TeamService teamService;

    /**
     * test id
     */
    private static final int TEST_ID = 2;

    /**
     * query list
     */
    @Test
    void testList() {
        List<Team> list = teamService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        Team team = teamService.getById(TEST_ID);
        Assert.notNull(team, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        Team team = new Team();
        team.setId(TEST_ID);
        team.setName("T");
        team.setLeader("Test");
        team.setLeaderId(3);
        team.setCreateTime(new Date());
        boolean res = teamService.updateById(team);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = teamService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        Team team = new Team();
        team.setId(TEST_ID);
        team.setName("Test");
        team.setLeader("Test");
        team.setLeaderId(3);
        team.setCreateTime(new Date());
        boolean res = teamService.save(team);
        Assert.isTrue(res, "");
    }
}
