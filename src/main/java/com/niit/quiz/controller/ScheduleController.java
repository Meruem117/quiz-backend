package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Schedule;
import com.niit.quiz.model.enums.StatusEnum;
import com.niit.quiz.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    /**
     * get schedule by id
     *
     * @param id schedule id
     * @return schedule item
     */
    @GetMapping("/get")
    public BaseResponse<Schedule> getScheduleById(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(scheduleService.getById(id));
    }

    /**
     * get schedule list
     *
     * @return schedule item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Schedule>> getScheduleList() {
        return ResultUtils.success(scheduleService.list());
    }

    /**
     * get schedule list by quiz id
     *
     * @param quizId quiz id
     * @return schedule item list
     */
    @GetMapping("/quiz")
    public BaseResponse<List<Schedule>> getScheduleListByQuizId(@RequestParam int quizId) {
        if (quizId < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("quiz_id", quizId);
        queryWrapper.orderByAsc("round");
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has started
     *
     * @return schedule item list
     */
    @GetMapping("/start")
    public BaseResponse<List<Schedule>> getScheduleStartList(@RequestParam int limit) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.START.getValue());
        if (limit > 0) {
            String limitSql = "limit " + limit;
            queryWrapper.last(limitSql);
        }
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has ended
     *
     * @return schedule item list
     */
    @GetMapping("/end")
    public BaseResponse<List<Schedule>> getScheduleEndList(@RequestParam int limit) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.END.getValue());
        if (limit > 0) {
            String limitSql = "limit " + limit;
            queryWrapper.last(limitSql);
        }
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has not started
     *
     * @return schedule item list
     */
    @GetMapping("/remain")
    public BaseResponse<List<Schedule>> getScheduleRemainList(@RequestParam int limit) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.NOT_START.getValue());
        if (limit > 0) {
            String limitSql = "limit " + limit;
            queryWrapper.last(limitSql);
        }
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }
}
