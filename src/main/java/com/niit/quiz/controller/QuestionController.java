package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.PassRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.model.enums.PassEnum;
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.model.entity.Question;
import com.niit.quiz.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
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
     * get question list by up id
     *
     * @param id up id
     * @return question item list
     */
    @GetMapping("/up")
    public BaseResponse<List<Question>> getQuestionListByUpId(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("up_id", id);
        return ResultUtils.success(questionService.list(questionQueryWrapper));
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
        if (StringUtils.isNotBlank(topic)) {
            questionQueryWrapper.eq("topic", topic);
        }
        questionQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        return ResultUtils.success(questionService.list(questionQueryWrapper));
    }

    /**
     * get question list by question of schedule
     *
     * @param question question of schedule
     * @return question list
     */
    @GetMapping("/question")
    public BaseResponse<List<Question>> getQuestionListBySchedule(@RequestParam String question) {
        String[] array = question.split(",");
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        Collection<Integer> ids = new ArrayList<>();
        for (String id : array) {
            ids.add(Integer.parseInt(id));
        }
        questionQueryWrapper.in("id", ids);
        return ResultUtils.success(questionService.list(questionQueryWrapper));
    }

    /**
     * add question
     *
     * @param question question item
     * @return question id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addQuestion(@RequestBody Question question) {
        if (question == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String datetime = DateUtils.getCurrentDateTime();
        question.setCreateTime(datetime);
        question.setUpdateTime(datetime);
        questionService.save(question);
        return ResultUtils.success(question.getId());
    }

    /**
     * pass question upload
     *
     * @param passRequest pass request
     * @return pass status
     */
    @PostMapping("/pass")
    public BaseResponse<Boolean> passQuestion(@RequestBody PassRequest passRequest) {
        Integer id = passRequest.getId();
        String pass = passRequest.getPass();
        if (id < 1 || !PassEnum.include(pass)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Question question = questionService.getById(id);
        question.setPass(pass);
        return ResultUtils.success(questionService.updateById(question));
    }

    /**
     * update question
     *
     * @param question question item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateQuestion(@RequestBody Question question) {
        if (question == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String datetime = DateUtils.getCurrentDateTime();
        question.setUpdateTime(datetime);
        return ResultUtils.success(questionService.updateById(question));
    }

    /**
     * logical delete question
     *
     * @param deleteRequest question id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestion(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(questionService.removeById(deleteRequest.getId()));
    }
}
