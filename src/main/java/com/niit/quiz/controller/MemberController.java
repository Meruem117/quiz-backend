package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.PageRequest;
import com.niit.quiz.base.request.PassRequest;
import com.niit.quiz.base.request.QuitRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.model.entity.Member;
import com.niit.quiz.model.enums.PassEnum;
import com.niit.quiz.model.enums.QuitEnum;
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
        memberQueryWrapper.eq("quit", QuitEnum.NOT_QUIT.getValue());
        return ResultUtils.success(memberService.list(memberQueryWrapper));
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
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", id);
        memberQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        memberQueryWrapper.eq("quit", QuitEnum.NOT_QUIT.getValue());
        return ResultUtils.success(memberService.list(memberQueryWrapper));
    }

    /**
     * check membership
     *
     * @param teamId team id
     * @param userId user id
     * @return check status
     */
    @GetMapping("/check")
    public BaseResponse<Integer> checkMembership(@RequestParam int teamId, @RequestParam int userId) {
        if (teamId < 1 || userId < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", teamId);
        memberQueryWrapper.eq("user_id", userId);
        memberQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        memberQueryWrapper.eq("quit", QuitEnum.NOT_QUIT.getValue());
        Member member = memberService.getOne(memberQueryWrapper);
        if (member == null) {
            return ResultUtils.error("No membership found");
        } else {
            return ResultUtils.success(member.getId());
        }
    }

    @PostMapping("/quit")
    public BaseResponse<Boolean> quitMembership(@RequestBody QuitRequest quitRequest) {
        Integer id = quitRequest.getId();
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        UpdateWrapper<Member> memberUpdateWrapper = new UpdateWrapper<>();
        memberUpdateWrapper.eq("id", id);
        memberUpdateWrapper.set("quit", QuitEnum.QUIT.getValue());
        return ResultUtils.success(memberService.update(memberUpdateWrapper));
    }

    /**
     * get member with pagination
     *
     * @param pageRequest page request
     * @return member item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Member>> getMemberPages(PageRequest pageRequest) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Member> memberPage = new Page<>(page, size);
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        return ResultUtils.success(memberService.page(memberPage, memberQueryWrapper));
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
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", member.getTeamId());
        memberQueryWrapper.eq("user_id", member.getUserId());
        Member originMember = memberService.getOne(memberQueryWrapper);
        if (ObjectUtils.isNotNull(originMember)) {
            if (Objects.equals(originMember.getQuit(), QuitEnum.NOT_QUIT.getValue())) {
                return ResultUtils.error("You have already applied before");
            } else {
                originMember.setPass(PassEnum.PENDING.getValue());
                originMember.setQuit(QuitEnum.NOT_QUIT.getValue());
                memberService.updateById(originMember);
                return ResultUtils.success(originMember.getId());
            }
        } else {
            String date = DateUtils.getCurrentDate();
            member.setJoinTime(date);
            member.setCreateTime(date);
            memberService.save(member);
            return ResultUtils.success(member.getId());
        }
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
