package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
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
     * get schedule list by quiz id
     *
     * @param quizId quiz id
     * @return schedule item list
     */
    @GetMapping("/get")
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
     * get schedule list
     *
     * @return schedule item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Schedule>> getScheduleList() {
        return ResultUtils.success(scheduleService.list());
    }

    /**
     * get quiz rounds that has started but not ended
     *
     * @return schedule item list
     */
    @GetMapping("/start")
    public BaseResponse<List<Schedule>> getScheduleStartList() {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_start", ScheduleStatusEnum.START.getValue());
        queryWrapper.eq("is_end", ScheduleStatusEnum.NOT_END.getValue());
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has ended
     *
     * @return schedule item list
     */
    @GetMapping("/end")
    public BaseResponse<List<Schedule>> getScheduleEndList() {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_end", ScheduleStatusEnum.END.getValue());
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }

    /**
     * get quiz rounds that has not started
     *
     * @return schedule item list
     */
    @GetMapping("/remain")
    public BaseResponse<List<Schedule>> getScheduleRemainList() {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_start", ScheduleStatusEnum.NOT_START.getValue());
        return ResultUtils.success(scheduleService.list(queryWrapper));
    }
}
