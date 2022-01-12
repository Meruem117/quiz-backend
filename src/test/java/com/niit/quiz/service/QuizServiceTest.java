package com.niit.quiz.service;

import com.niit.quiz.model.entity.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class QuizServiceTest {
    @Resource
    private QuizService quizService;

    /**
     * test id
     */
    private static final int TEST_ID = 3;

    /**
     * query list
     */
    @Test
    void testList() {
        List<Quiz> list = quizService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        Quiz quiz = quizService.getById(TEST_ID);
        Assert.notNull(quiz, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        Quiz quiz = new Quiz();
        quiz.setId(TEST_ID);
        quiz.setRound(5);
        boolean res = quizService.updateById(quiz);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = quizService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        Quiz quiz = new Quiz();
        quiz.setId(TEST_ID);
        quiz.setQuiz("Third Quiz");
        quiz.setTopic("Maths");
        quiz.setRound(1);
        quiz.setCreator("N");
        quiz.setCreatorId(2);
        quiz.setQuestion("1-2-3");
        quiz.setWinner(null);
        quiz.setWinnerId(null);
        quiz.setIsTeam(null);
        boolean res = quizService.save(quiz);
        Assert.isTrue(res, "");
    }
}
