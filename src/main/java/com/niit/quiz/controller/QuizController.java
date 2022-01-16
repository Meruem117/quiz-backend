package com.niit.quiz.controller;

import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Quiz;
import com.niit.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Resource
    private QuizService quizService;

    /**
     * get quiz detail by id
     *
     * @param id quiz id
     * @return quiz item
     */
    @GetMapping("/get")
    public BaseResponse<Quiz> getQuizById(@RequestParam int id) {
        return ResultUtils.success(quizService.getById(id));
    }

    /**
     * get quiz list
     *
     * @return quiz item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Quiz>> getQuizList() {
        return ResultUtils.success(quizService.list());
    }
}
