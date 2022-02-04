package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Result;
import com.niit.quiz.model.enums.IsTeamEnum;
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
     * get user result list by user id
     *
     * @param id user id
     * @return result item list
     */
    @GetMapping("/users")
    public BaseResponse<List<Result>> getUserResultListByUserId(@RequestParam int id) {
        QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
        resultQueryWrapper.eq("is_team", IsTeamEnum.USER.getValue());
        resultQueryWrapper.eq("participant_id", id);
        return ResultUtils.success(resultService.list(resultQueryWrapper));
    }

    /**
     * get team result list by team id
     *
     * @param id team id
     * @return result item list
     */
    @GetMapping("/teams")
    public BaseResponse<List<Result>> getTeamResultListByTeamId(@RequestParam int id) {
        QueryWrapper<Result> resultQueryWrapper = new QueryWrapper<>();
        resultQueryWrapper.eq("is_team", IsTeamEnum.TEAM.getValue());
        resultQueryWrapper.eq("participant_id", id);
        return ResultUtils.success(resultService.list(resultQueryWrapper));
    }
}
