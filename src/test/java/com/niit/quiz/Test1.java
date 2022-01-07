package com.niit.quiz;

import com.niit.quiz.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class Test1 {
    @Resource
    private TestMapper mapper;

    @Test
    void test() {
        List<com.niit.quiz.entity.Test> list = mapper.getAll();
        System.out.println(list);
    }
}
