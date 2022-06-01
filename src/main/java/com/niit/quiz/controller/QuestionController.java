package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.DisableRequest;
import com.niit.quiz.base.request.PageRequest;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public BaseResponse<Question> getQuestionById(@RequestParam Integer id) {
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
    public BaseResponse<List<Question>> getQuestionListByUpId(@RequestParam Integer id) {
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
    public BaseResponse<List<Question>> getQuestionListByTopicName(@RequestParam String topic) {
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(topic)) {
            questionQueryWrapper.eq("topic", topic);
        }
        questionQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        return ResultUtils.success(questionService.list(questionQueryWrapper));
    }

    /**
     * get question list by topic id
     *
     * @param id topic id
     * @return question item list
     */
    @GetMapping("/topic")
    public BaseResponse<List<Question>> getQuestionListByTopicId(@RequestParam Integer id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("topic_id", id);
        questionQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        return ResultUtils.success(questionService.list(questionQueryWrapper));
    }

    /**
     * get question list by question of schedule
     *
     * @param question question of schedule
     * @return question list
     */
    @GetMapping("/schedule")
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
     * get question with pagination
     *
     * @param pageRequest page request
     * @return question item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Question>> getQuestionPages(PageRequest pageRequest, Integer topicId) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Question> questionPage = new Page<>(page, size);
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotNull(topicId)) {
            questionQueryWrapper.eq("topic_id", topicId);
        }
        return ResultUtils.success(questionService.page(questionPage, questionQueryWrapper));
    }

    /**
     * add question
     *
     * @param question question item
     * @return question id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addQuestion(@RequestBody Question question) {
        if (ObjectUtils.isNull(question)) {
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
        String ids = passRequest.getIds();
        String pass = passRequest.getPass();
        if (StringUtils.isBlank(ids) || !PassEnum.include(pass)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        LambdaUpdateWrapper<Question> questionLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        questionLambdaUpdateWrapper.in(Question::getId, idList);
        questionLambdaUpdateWrapper.set(Question::getPass, pass);
        return ResultUtils.success(questionService.update(questionLambdaUpdateWrapper));
    }

    /**
     * disable questions
     *
     * @param disableRequest disable request
     * @return disable status
     */
    @PostMapping("/disable")
    public BaseResponse<Boolean> disableQuestion(@RequestBody DisableRequest disableRequest) {
        String ids = disableRequest.getIds();
        if (StringUtils.isBlank(ids)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return ResultUtils.success(questionService.removeByIds(idList));
    }

    /**
     * update question
     *
     * @param question question item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateQuestion(@RequestBody Question question) {
        if (ObjectUtils.isNull(question)) {
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
        if (ObjectUtils.isNull(deleteRequest) || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(questionService.removeById(deleteRequest.getId()));
    }
}
