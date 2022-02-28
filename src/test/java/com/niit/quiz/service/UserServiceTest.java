package com.niit.quiz.service;

import com.niit.quiz.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    /**
     * test id
     */
    private static final int TEST_ID = 3;

    /**
     * query list
     */
    @Test
    void testList() {
        List<User> list = userService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * query one line
     */
    @Test
    void testOne() {
        User user = userService.getById(TEST_ID);
        Assert.notNull(user, "");
    }

    /**
     * update
     */
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(TEST_ID);
        user.setName("T");
        boolean res = userService.updateById(user);
        Assert.isTrue(res, "");
    }

    /**
     * delete
     */
    @Test
    void testDelete() {
        boolean res = userService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * insert
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setId(TEST_ID);
        user.setEmail("123456789@number.com");
        user.setName("Test");
        user.setPassword("123456");
        user.setCreateTime("2022-01-08");
        boolean res = userService.save(user);
        Assert.isTrue(res, "");
    }
}
