package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public BaseResponse<List<Member>> getTeamListByUserId(@RequestParam Integer id, @RequestParam String pass, @RequestParam Integer quit) {
        if (id < 1 || !PassEnum.include(pass) || !QuitEnum.include(quit)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("user_id", id);
        memberQueryWrapper.eq("pass", pass);
        memberQueryWrapper.eq("quit", quit);
        return ResultUtils.success(memberService.list(memberQueryWrapper));
    }

    /**
     * get a team's members
     *
     * @param id team id
     * @return member item list
     */
    @GetMapping("/user")
    public BaseResponse<List<Member>> getUserListByTeamId(@RequestParam Integer id, @RequestParam String pass, @RequestParam Integer quit) {
        if (id < 1 || !PassEnum.include(pass) || !QuitEnum.include(quit)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", id);
        memberQueryWrapper.eq("pass", pass);
        memberQueryWrapper.eq("quit", quit);
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
    public BaseResponse<Integer> checkMembership(@RequestParam Integer teamId, @RequestParam Integer userId) {
        if (teamId < 1 || userId < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", teamId);
        memberQueryWrapper.eq("user_id", userId);
        memberQueryWrapper.eq("pass", PassEnum.PASS.getValue());
        memberQueryWrapper.eq("quit", QuitEnum.NOT_QUIT.getValue());
        Member member = memberService.getOne(memberQueryWrapper);
        if (ObjectUtils.isNull(member)) {
            return ResultUtils.error("No membership found");
        } else {
            return ResultUtils.success(member.getId());
        }
    }

    /**
     * quit membership
     *
     * @param quitRequest quit request
     * @return quit status
     */
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
    public BaseResponse<IPage<Member>> getMemberPages(PageRequest pageRequest, Integer teamId) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Member> memberPage = new Page<>(page, size);
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotNull(teamId)) {
            memberQueryWrapper.eq("team_id", teamId);
        }
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
        if (ObjectUtils.isNull(member)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("team_id", member.getTeamId());
        memberQueryWrapper.eq("user_id", member.getUserId());
        Member originMember = memberService.getOne(memberQueryWrapper);
        String date = DateUtils.getCurrentDate();
        if (ObjectUtils.isNotNull(originMember)) {
            if (Objects.equals(originMember.getQuit(), QuitEnum.NOT_QUIT.getValue())) {
                return ResultUtils.error("You have already applied before");
            } else {
                originMember.setPass(PassEnum.PENDING.getValue());
                originMember.setQuit(QuitEnum.NOT_QUIT.getValue());
                originMember.setApplyTime(date);
                memberService.updateById(originMember);
                return ResultUtils.success(originMember.getId());
            }
        } else {
            member.setJoinTime(date);
            member.setApplyTime(date);
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
        String ids = passRequest.getIds();
        String pass = passRequest.getPass();
        if (StringUtils.isBlank(ids) || !PassEnum.include(pass)) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        LambdaUpdateWrapper<Member> memberLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        memberLambdaUpdateWrapper.in(Member::getId, idList);
        memberLambdaUpdateWrapper.set(Member::getPass, pass);
        return ResultUtils.success(memberService.update(memberLambdaUpdateWrapper));
    }

    /**
     * dismiss members by team id
     *
     * @param deleteRequest team id
     * @return dismiss status
     */
    @PostMapping("/dismiss")
    public BaseResponse<Boolean> dismissMembersByTeamId(@RequestBody DeleteRequest deleteRequest) {
        Integer id = deleteRequest.getId();
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        LambdaUpdateWrapper<Member> memberLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        memberLambdaUpdateWrapper.eq(Member::getTeamId, id);
        memberLambdaUpdateWrapper.set(Member::getQuit, QuitEnum.QUIT.getValue());
        return ResultUtils.success(memberService.update(memberLambdaUpdateWrapper));
    }

    /**
     * logical delete member
     *
     * @param deleteRequest member id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody DeleteRequest deleteRequest) {
        if (ObjectUtils.isNull(deleteRequest) || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(memberService.removeById(deleteRequest.getId()));
    }
}
