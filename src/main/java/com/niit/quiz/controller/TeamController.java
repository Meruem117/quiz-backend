package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.model.entity.Team;
import com.niit.quiz.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Resource
    private TeamService teamService;

    /**
     * get team by id
     *
     * @param id team id
     * @return team item
     */
    @GetMapping("/get")
    public BaseResponse<Team> getTeamById(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(teamService.getById(id));
    }

    /**
     * get team list
     *
     * @return team item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Team>> getTeamList() {
        return ResultUtils.success(teamService.list());
    }

    /**
     * get team list by leader id
     *
     * @param id leader id
     * @return team item list
     */
    @GetMapping("/leader")
    public BaseResponse<List<Team>> getTeamListByLeaderId(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>();
        teamQueryWrapper.eq("leader_id", id);
        return ResultUtils.success(teamService.list(teamQueryWrapper));
    }
}
