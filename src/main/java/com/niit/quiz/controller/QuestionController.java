package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Question;
import com.niit.quiz.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    /**
     * get question by id
     *
     * @param id question id
     * @return question item
     */
    @GetMapping("/get")
    public BaseResponse<Question> getQuestionById(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(questionService.getById(id));
    }

    /**
     * get question list by topic name
     *
     * @param topic topic name
     * @return question item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Question>> getQuestionListByTopicId(@RequestParam String topic) {
        if (topic == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("topic", topic);
        return ResultUtils.success(questionService.list(questionQueryWrapper));
    }
}
