package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.model.enums.IsTeamEnum;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.model.entity.Result;
import com.niit.quiz.service.ResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Resource
    private ResultService resultService;

    /**
     * get result list by participant id
     *
     * @param id     participant id
     * @param isTeam whether the participant is team
     * @return result item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Result>> getUserResultListByUserId(@RequestParam int id, @RequestParam int isTeam) {
        if (id < 1 || !IsTeamEnum.include(isTeam)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
        resultQueryWrapper.eq("is_team", isTeam);
        resultQueryWrapper.eq("participant_id", id);
        return ResultUtils.success(resultService.list(resultQueryWrapper));
    }

    /**
     * check whether the participant sign up for the quiz
     *
     * @param scheduleId    schedule id
     * @param participantId participant id
     * @param isTeam        whether the participant is team
     * @return check boolean result
     */
    @GetMapping("/check")
    public BaseResponse<Boolean> checkParticipant(@RequestParam int scheduleId, @RequestParam int participantId, @RequestParam int isTeam) {
        if (scheduleId < 1 || participantId < 1 || !IsTeamEnum.include(isTeam)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
        resultQueryWrapper.eq("is_team", isTeam);
        resultQueryWrapper.eq("schedule_id", scheduleId);
        resultQueryWrapper.eq("participant_id", participantId);
        return ResultUtils.success(resultService.count(resultQueryWrapper) == 1);
    }
}
