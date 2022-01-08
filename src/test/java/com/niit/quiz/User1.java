package com.niit.quiz;

import com.niit.quiz.entity.User;
import com.niit.quiz.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class User1 {
    @Resource
    private UserMapper mapper;

    @Test
    void test() {
        List<User> list = mapper.selectList(null);
        System.out.println(list);
    }
}
