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
import com.niit.quiz.model.entity.Team;
import com.niit.quiz.service.TeamService;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<Team> getTeamById(@RequestParam Integer id) {
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
    public BaseResponse<List<Team>> getTeamListByLeaderId(@RequestParam Integer id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>();
        teamQueryWrapper.eq("leader_id", id);
        return ResultUtils.success(teamService.list(teamQueryWrapper));
    }

    /**
     * get team with pagination
     *
     * @param pageRequest page request
     * @return team item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Team>> getTeamPages(PageRequest pageRequest) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Team> teamPage = new Page<>(page, size);
        QueryWrapper<Team> teamQueryWrapper = new QueryWrapper<>();
        return ResultUtils.success(teamService.page(teamPage, teamQueryWrapper));
    }

    /**
     * add team
     *
     * @param team team item
     * @return team id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addTeam(@RequestBody Team team) {
        if (ObjectUtils.isNull(team)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String date = DateUtils.getCurrentDateTime();
        team.setCreateTime(date);
        teamService.save(team);
        return ResultUtils.success(team.getId());
    }

    /**
     * update team
     *
     * @param team team item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody Team team) {
        if (ObjectUtils.isNull(team)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(teamService.updateById(team));
    }

    /**
     * logical delete team
     *
     * @param deleteRequest team id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody DeleteRequest deleteRequest) {
        if (ObjectUtils.isNull(deleteRequest) || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(teamService.removeById(deleteRequest.getId()));
    }
}
