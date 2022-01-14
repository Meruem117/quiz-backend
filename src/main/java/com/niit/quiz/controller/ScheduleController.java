package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Schedule;
import com.niit.quiz.model.enums.ScheduleStatusEnum;
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
     * get schedule info of current round of quiz by quiz id
     *
     * @param quizId quiz id
     * @return schedule item
     */
    @GetMapping("/get")
    public BaseResponse<Schedule> getScheduleByQuizId(@RequestParam int quizId) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("quiz_id", quizId);
        queryWrapper.eq("is_start", ScheduleStatusEnum.START.getValue());
        queryWrapper.eq("is_end", ScheduleStatusEnum.NOT_END.getValue());
        return ResultUtils.success(scheduleService.getOne(queryWrapper));
    }

    /**
     * get quiz rounds that has started but not ended
     *
     * @param limit limit rows
     * @return schedule item list
     */
    @GetMapping("/start")
    public BaseResponse<List<Schedule>> getScheduleStartList(@RequestParam int limit) {
        String limitSql = "limit " + limit;
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_start", ScheduleStatusEnum.START.getValue());
        queryWrapper.eq("is_end", ScheduleStatusEnum.NOT_END.getValue());
        queryWrapper.orderByDesc("start_time");
        queryWrapper.last(limitSql);
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has ended
     *
     * @param limit limit rows
     * @return schedule item list
     */
    @GetMapping("/end")
    public BaseResponse<List<Schedule>> getScheduleEndList(@RequestParam int limit) {
        String limitSql = "limit " + limit;
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_end", ScheduleStatusEnum.END.getValue());
        queryWrapper.orderByDesc("end_time");
        queryWrapper.last(limitSql);
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has not ended
     *
     * @param limit limit rows
     * @return schedule item list
     */
    @GetMapping("/remain")
    public BaseResponse<List<Schedule>> getScheduleNotEndList(@RequestParam int limit) {
        String limitSql = "limit " + limit;
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_end", ScheduleStatusEnum.NOT_END.getValue());
        queryWrapper.orderByDesc("end_time");
        queryWrapper.last(limitSql);
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }
}
