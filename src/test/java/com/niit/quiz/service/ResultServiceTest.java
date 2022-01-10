package com.niit.quiz.service;

import com.niit.quiz.model.entity.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ResultServiceTest {
    @Resource
    private ResultService resultService;

    /**
     * test id
     */
    private static final int TEST_ID = 2;

    /**
     * query list
     */
    @Test
    void testList() {
        List<Result> list = resultService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        Result team = resultService.getById(TEST_ID);
        Assert.notNull(team, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        Result result = new Result();
        result.setId(TEST_ID);
        result.setMark(4);
        boolean res = resultService.updateById(result);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = resultService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        Result result = new Result();
        result.setId(TEST_ID);
        result.setQuizId(1);
        result.setRound(1);
        result.setIsEnd(1);
        result.setParticipantId(2);
        result.setIsTeam(1);
        result.setMark(2);
        result.setErrorList("1-2");
        result.setIsOut(1);
        boolean res = resultService.save(result);
        Assert.isTrue(res, "");
    }
}
