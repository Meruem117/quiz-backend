package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.PageRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.model.entity.Quiz;
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<Quiz> getQuizById(@RequestParam Integer id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
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

    /**
     * get quiz with pagination
     *
     * @param pageRequest page request
     * @return quiz item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Quiz>> getQuizPages(PageRequest pageRequest) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Quiz> quizPage = new Page<>(page, size);
        QueryWrapper<Quiz> quizQueryWrapper = new QueryWrapper<>();
        return ResultUtils.success(quizService.page(quizPage, quizQueryWrapper));
    }

    /**
     * add quiz
     *
     * @param quiz quiz item
     * @return quiz id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addQuiz(@RequestBody Quiz quiz) {
        if (ObjectUtils.isNull(quiz)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String date = DateUtils.getCurrentDate();
        quiz.setCreateTime(date);
        quizService.save(quiz);
        return ResultUtils.success(quiz.getId());
    }

    /**
     * update quiz
     *
     * @param quiz quiz item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateQuiz(@RequestBody Quiz quiz) {
        if (ObjectUtils.isNull(quiz)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(quizService.updateById(quiz));
    }

    /**
     * logical delete quiz
     *
     * @param deleteRequest quiz id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuiz(@RequestBody DeleteRequest deleteRequest) {
        if (ObjectUtils.isNull(deleteRequest) || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(quizService.removeById(deleteRequest.getId()));
    }
}
