package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.LoginRequest;
import com.niit.quiz.base.request.SearchRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.utils.ResultUtils;
import com.niit.quiz.base.response.CheckResponse;
import com.niit.quiz.model.entity.User;
import com.niit.quiz.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * get user by id
     *
     * @param id user id
     * @return user item
     */
    @GetMapping("/get")
    public BaseResponse<User> getUserById(@RequestParam int id) {
        if (id < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        User user = userService.getById(id);
        if (user == null) {
            return ResultUtils.error("This user has been cancelled");
        }
        return ResultUtils.success(user);
    }

    /**
     * get user list
     *
     * @return user item list
     */
    @GetMapping("/list")
    public BaseResponse<List<User>> getUserList() {
        return ResultUtils.success(userService.list());
    }

    /**
     * check user password
     *
     * @param request email & password
     * @return check result
     */
    @PostMapping("/check")
    public BaseResponse<CheckResponse> checkUserPassword(@RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        Boolean check = Objects.equals(password, user.getPassword());
        return ResultUtils.success(new CheckResponse(check, user));
    }

    /**
     * add user
     *
     * @param user user info
     * @return user id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addUser(@RequestBody User user) {
        if (user == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        userService.save(user);
        return ResultUtils.success(user.getId());
    }

    /**
     * update user
     *
     * @param user user info
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody User user) {
        if (user == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(userService.updateById(user));
    }

    /**
     * logical delete user
     *
     * @param deleteRequest user id
     * @return delete status
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(userService.removeById(deleteRequest.getId()));
    }

    @PostMapping("/search")
    public BaseResponse<IPage<User>> getUserSearchList(SearchRequest request) {
        Integer pageNum = request.getPage();
        Integer pageSize = request.getSize();
        String key = request.getKey();

        if (pageNum < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            userQueryWrapper.like("name", key);
        }
        return ResultUtils.success(userService.page(new Page<>(pageNum, pageSize), userQueryWrapper));
    }
}
