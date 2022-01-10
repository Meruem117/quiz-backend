package com.niit.quiz.service;

import com.niit.quiz.model.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class QuestionServiceTest {
    @Resource
    private QuestionService questionService;

    /**
     * test id
     */
    private static final int TEST_ID = 3;

    /**
     * query list
     */
    @Test
    void testList() {
        List<Question> list = questionService.list();
        System.out.println(list);
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        Question question = questionService.getById(TEST_ID);
        Assert.notNull(question, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        Question question = new Question();
        question.setId(TEST_ID);
        question.setAnswer("c");
        question.setScore(2);
        boolean res = questionService.updateById(question);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = questionService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        Question question = new Question();
        question.setId(TEST_ID);
        question.setQuestion("3+1=?");
        question.setTopic("Maths");
        question.setType(1);
        question.setOptionA("1");
        question.setOptionB("2");
        question.setOptionC("3");
        question.setOptionD("4");
        question.setAnswer("d");
        question.setScore(2);
        question.setCreateTime(new Date());
        question.setUpdateTime(new Date());
        boolean res = questionService.save(question);
        Assert.isTrue(res, "");
    }
}
