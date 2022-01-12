package com.niit.quiz.service;

import com.niit.quiz.model.entity.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ScheduleServiceTest {
    @Resource
    private ScheduleService scheduleService;

    /**
     * test id
     */
    private static final int TEST_ID = 2;

    /**
     * query list
     */
    @Test
    void testList() {
        List<Schedule> list = scheduleService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        Schedule schedule = scheduleService.getById(TEST_ID);
        Assert.notNull(schedule, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        Schedule schedule = new Schedule();
        schedule.setId(TEST_ID);
        schedule.setMark(4);
        boolean res = scheduleService.updateById(schedule);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = scheduleService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        Schedule schedule = new Schedule();
        schedule.setId(TEST_ID);
        schedule.setQuizId(1);
        schedule.setRound(1);
        schedule.setIsEnd(1);
        schedule.setParticipantId(2);
        schedule.setIsTeam(1);
        schedule.setMark(2);
        schedule.setErrorList("1-2");
        schedule.setIsOut(1);
        boolean res = scheduleService.save(schedule);
        Assert.isTrue(res, "");
    }
}
