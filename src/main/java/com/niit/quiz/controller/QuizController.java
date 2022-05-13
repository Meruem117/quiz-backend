package com.niit.quiz.controller;

import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
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
    public BaseResponse<Quiz> getQuizById(@RequestParam int id) {
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
     * add quiz
     *
     * @param quiz quiz item
     * @return quiz id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addQuiz(@RequestBody Quiz quiz) {
        if (quiz == null) {
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
        if (quiz == null) {
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
    @PostMapping("/update")
    public BaseResponse<Boolean> deleteQuiz(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(quizService.removeById(deleteRequest.getId()));
    }
}
