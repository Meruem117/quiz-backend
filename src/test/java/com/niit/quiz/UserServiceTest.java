package com.niit.quiz;

import com.niit.quiz.entity.User;
import com.niit.quiz.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    /**
     * 测试查询，插入，更新，删除的用户id
     */
    private static final int TEST_ID = 3;

    /**
     * 测试查询列表
     */
    @Test
    void testList() {
        List<User> list = userService.list();
        Assert.notEmpty(list, "");
    }

    /**
     * 测试查询单条数据
     */
    @Test
    void testOne() {
        User user = userService.getById(TEST_ID);
        Assert.notNull(user, "");
    }

    /**
     * 测试更新
     */
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(TEST_ID);
        user.setEmail("123456789@number.com");
        user.setName("T");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        boolean res = userService.updateById(user);
        Assert.isTrue(res, "");
    }

    /**
     * 测试删除
     */
    @Test
    void testDelete() {
        boolean res = userService.removeById(TEST_ID);
        Assert.isTrue(res, "");
    }

    /**
     * 测试插入
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setId(TEST_ID);
        user.setEmail("123456789@number.com");
        user.setName("Test");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        boolean res = userService.save(user);
        Assert.isTrue(res, "");
    }
}
