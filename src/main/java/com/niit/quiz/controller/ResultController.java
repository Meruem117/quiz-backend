package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.model.entity.Result;
import com.niit.quiz.model.enums.IsTakeEnum;
import com.niit.quiz.model.enums.IsTeamEnum;
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.service.ResultService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Resource
    private ResultService resultService;

    /**
     * get result by id
     *
     * @param id result id
     * @return result item
     */
    @GetMapping("/get")
    public BaseResponse<Result> getResultById(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(resultService.getById(id));
    }

    /**
     * get result list by participant id
     *
     * @param id     participant id
     * @param isTeam whether the participant is team
     * @return result item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Result>> getResultListByParticipantId(@RequestParam int id, @RequestParam int isTeam) {
        if (id < 1 || !IsTeamEnum.include(isTeam)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
        resultQueryWrapper.eq("is_team", isTeam);
        resultQueryWrapper.eq("participant_id", id);
        return ResultUtils.success(resultService.list(resultQueryWrapper));
    }

    /**
     * get result item when attend a quiz round
     *
     * @param scheduleId    schedule id
     * @param participantId participant id
     * @param isTeam        whether the participant is team
     * @return result item
     */
    @GetMapping("/attend")
    public BaseResponse<Result> getResultWhenAttend(@RequestParam int scheduleId, @RequestParam int participantId,
                                                    @RequestParam int isTeam) {
        if (scheduleId < 1 || participantId < 1 || !IsTeamEnum.include(isTeam)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
        resultQueryWrapper.eq("is_team", isTeam);
        resultQueryWrapper.eq("schedule_id", scheduleId);
        resultQueryWrapper.eq("participant_id", participantId);
        return ResultUtils.success(resultService.getOne(resultQueryWrapper));
    }

    /**
     * add result, sign up
     *
     * @param result result item
     * @return result id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addResult(@RequestBody Result result) {
        if (result == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String datetime = DateUtils.getCurrentDateTime();
        result.setTakeTime(datetime);
        result.setCreateTime(datetime);
        resultService.save(result);
        return ResultUtils.success(result.getId());
    }

    /**
     * submit result
     *
     * @param result result item
     * @return submit status
     */
    @PostMapping("/submit")
    public BaseResponse<Boolean> submitResult(@RequestBody Result result) {
        if (result == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String datetime = DateUtils.getCurrentDateTime();
        result.setTakeTime(datetime);
        result.setIsTake(IsTakeEnum.TAKE.getValue());
        return ResultUtils.success(resultService.updateById(result));
    }

    /**
     * update result
     *
     * @param result result item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateResult(@RequestBody Result result) {
        if (result == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(resultService.updateById(result));
    }

    /**
     * logical delete result
     *
     * @param deleteRequest result id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteResult(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(resultService.removeById(deleteRequest.getId()));
    }
}
