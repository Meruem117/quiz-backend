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
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.model.entity.Schedule;
import com.niit.quiz.model.enums.StatusEnum;
import com.niit.quiz.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

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
        QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
        scheduleQueryWrapper.eq("quiz_id", quizId);
        scheduleQueryWrapper.orderByAsc("round");
        return ResultUtils.success(scheduleService.list(scheduleQueryWrapper));
    }

    /**
     * get quiz rounds that has started
     *
     * @return schedule item list
     */
    @GetMapping("/start")
    public BaseResponse<List<Schedule>> getScheduleStartList(@RequestParam int limit) {
        QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
        scheduleQueryWrapper.eq("status", StatusEnum.START.getValue());
        if (limit > 0) {
            String limitSql = "limit " + limit;
            scheduleQueryWrapper.last(limitSql);
        }
        return ResultUtils.success(scheduleService.list(scheduleQueryWrapper));
    }

    /**
     * get quiz rounds that has ended
     *
     * @return schedule item list
     */
    @GetMapping("/end")
    public BaseResponse<List<Schedule>> getScheduleEndList(@RequestParam int limit) {
        QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
        scheduleQueryWrapper.eq("status", StatusEnum.END.getValue());
        if (limit > 0) {
            String limitSql = "limit " + limit;
            scheduleQueryWrapper.last(limitSql);
        }
        return ResultUtils.success(scheduleService.list(scheduleQueryWrapper));
    }

    /**
     * get quiz rounds that has not started
     *
     * @return schedule item list
     */
    @GetMapping("/remain")
    public BaseResponse<List<Schedule>> getScheduleRemainList(@RequestParam int limit) {
        QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
        scheduleQueryWrapper.eq("status", StatusEnum.NOT_START.getValue());
        if (limit > 0) {
            String limitSql = "limit " + limit;
            scheduleQueryWrapper.last(limitSql);
        }
        return ResultUtils.success(scheduleService.list(scheduleQueryWrapper));
    }

    /**
     * get schedule with pagination
     *
     * @param pageRequest page request
     * @return schedule item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Schedule>> getSchedulePages(PageRequest pageRequest, Integer quizId) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Schedule> schedulePage = new Page<>(page, size);
        QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotNull(quizId)) {
            scheduleQueryWrapper.eq("quiz_id", quizId);
        }
        return ResultUtils.success(scheduleService.page(schedulePage, scheduleQueryWrapper));
    }

    /**
     * add schedule
     *
     * @param schedule schedule item
     * @return schedule id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addSchedule(@RequestBody Schedule schedule) {
        if (schedule == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String date = DateUtils.getCurrentDateTime();
        schedule.setCreateTime(date);
        scheduleService.save(schedule);
        return ResultUtils.success(schedule.getId());
    }

    /**
     * update schedule
     *
     * @param schedule schedule item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateSchedule(@RequestBody Schedule schedule) {
        if (schedule == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(scheduleService.updateById(schedule));
    }

    /**
     * logical delete schedule
     *
     * @param deleteRequest schedule id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteSchedule(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(scheduleService.removeById(deleteRequest.getId()));
    }
}
