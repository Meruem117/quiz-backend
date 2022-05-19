package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.PassRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.model.entity.Member;
import com.niit.quiz.model.enums.PassEnum;
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.service.MemberService;
import org.springframework.web.bind.annotation.*;

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
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("user_id", id);
        memberQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        return ResultUtils.success(memberService.list(memberQueryWrapper));
    }

    /**
     * get a member's members
     *
     * @param id member id
     * @return member item list
     */
    @GetMapping("/user")
    public BaseResponse<List<Member>> getUserListByTeamId(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", id);
        memberQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        return ResultUtils.success(memberService.list(memberQueryWrapper));
    }

    /**
     * add member
     *
     * @param member member item
     * @return member id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addMember(@RequestBody Member member) {
        if (member == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String date = DateUtils.getCurrentDateTime();
        member.setJoinTime(date);
        memberService.save(member);
        return ResultUtils.success(member.getId());
    }

    /**
     * pass member application
     *
     * @param passRequest pass request
     * @return pass status
     */
    @PostMapping("/pass")
    public BaseResponse<Boolean> passMember(@RequestBody PassRequest passRequest) {
        Integer id = passRequest.getId();
        String pass = passRequest.getPass();
        if (id < 1 || !PassEnum.include(pass)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Member member = memberService.getById(id);
        member.setPass(pass);
        return ResultUtils.success(memberService.updateById(member));
    }

    /**
     * logical delete member
     *
     * @param deleteRequest member id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(memberService.removeById(deleteRequest.getId()));
    }
}
