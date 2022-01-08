package com.niit.quiz.service;

import com.niit.quiz.entity.Team;
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
     * 测试查询，插入，更新，删除的组id
     */
    private static final int TEST_ID = 2;

    /**
     * 测试查询列表
     */
    @Test
    void testList() {
        List<Team> list = teamService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * 测试查询单条数据
     */
    @Test
    void testOne() {
        Team team = teamService.getById(TEST_ID);
        Assert.notNull(team, "");
    }

    /**
     * 测试更新
     */
    @Test
    void testUpdate() {
        Team team = new Team();
        team.setId(TEST_ID);
        team.setName("T");
        team.setCreator("Test");
        team.setCreatorId(3);
        team.setCreateTime(new Date());
        boolean res = teamService.updateById(team);
        Assert.isTrue(res, "");
    }

    /**
     * 测试删除
     */
    @Test
    void testDelete() {
        boolean res = teamService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * 测试插入
     */
    @Test
    void testInsert() {
        Team team = new Team();
        team.setId(TEST_ID);
        team.setName("Test");
        team.setCreator("Test");
        team.setCreatorId(3);
        team.setCreateTime(new Date());
        boolean res = teamService.save(team);
        Assert.isTrue(res, "");
    }
}
