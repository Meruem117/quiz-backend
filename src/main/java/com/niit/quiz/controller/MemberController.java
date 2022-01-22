package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Member;
import com.niit.quiz.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    /**
     * get a user's teams
     *
     * @param id user id
     * @return member item list
     */
    @GetMapping("/team")
    public BaseResponse<List<Member>> getTeamListByUserId(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return ResultUtils.success(memberService.list(queryWrapper));
    }

    /**
     * get a team's members
     *
     * @param id team id
     * @return member item list
     */
    @GetMapping("/user")
    public BaseResponse<List<Member>> getUserListByTeamId(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("team_id", id);
        return ResultUtils.success(memberService.list(queryWrapper));
    }
}
