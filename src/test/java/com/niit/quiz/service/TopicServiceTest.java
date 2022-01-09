package com.niit.quiz.service;

import com.niit.quiz.model.entity.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TopicServiceTest {
    @Resource
    private TopicService topicService;

    /**
     * test id
     */
    private static final int TEST_ID = 1;

    /**
     * query list
     */
    @Test
    void testList() {
        List<Topic> list = topicService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        Topic topic = topicService.getById(TEST_ID);
        Assert.notNull(topic, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        Topic topic = new Topic();
        topic.setId(TEST_ID);
        topic.setTopic("China");
        boolean res = topicService.updateById(topic);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = topicService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        Topic topic = new Topic();
        topic.setId(TEST_ID);
        topic.setTopic("Chinese");
        boolean res = topicService.save(topic);
        Assert.isTrue(res, "");
    }
}
