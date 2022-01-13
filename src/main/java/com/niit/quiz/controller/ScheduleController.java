package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Schedule;
import com.niit.quiz.model.enums.ScheduleConditionEnum;
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
     * get schedule info by id
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Schedule> getScheduleById(@RequestParam int id) {
        return ResultUtils.success(scheduleService.getById(id));
    }

    /**
     * get quiz rounds that has started but not ended
     *
     * @param limit
     * @return
     */
    @GetMapping("/start")
    public BaseResponse<List<Schedule>> getScheduleStartList(@RequestParam int limit) {
        String limitSql = "limit " + limit;
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_start", ScheduleConditionEnum.START.getValue());
        queryWrapper.eq("is_end", ScheduleConditionEnum.NOT_END.getValue());
        queryWrapper.orderByDesc("start_time");
        queryWrapper.last(limitSql);
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has ended
     *
     * @param limit
     * @return
     */
    @GetMapping("/end")
    public BaseResponse<List<Schedule>> getScheduleEndList(@RequestParam int limit) {
        String limitSql = "limit " + limit;
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_end", ScheduleConditionEnum.END.getValue());
        queryWrapper.orderByDesc("end_time");
        queryWrapper.last(limitSql);
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has not ended
     *
     * @param limit
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<List<Schedule>> getScheduleNotEndList(@RequestParam int limit) {
        String limitSql = "limit " + limit;
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_end", ScheduleConditionEnum.NOT_END.getValue());
        queryWrapper.orderByDesc("end_time");
        queryWrapper.last(limitSql);
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }
}
