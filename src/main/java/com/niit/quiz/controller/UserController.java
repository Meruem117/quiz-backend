package com.niit.quiz.controller;

import com.niit.quiz.base.BaseResponse;
import com.niit.quiz.base.DeleteRequest;
import com.niit.quiz.base.ResultUtils;
import com.niit.quiz.entity.User;
import com.niit.quiz.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/get")
    public BaseResponse<User> getUserById(@RequestParam int id) throws Exception {
        User user = userService.getById(id);
        return ResultUtils.success(user);
    }

    @PostMapping("/insert")
    public BaseResponse<Integer> insertUser(@RequestBody User user) throws Exception {
        if (user == null) {
            throw new Exception();
        }
        user.setCreateTime(new Date());
        userService.save(user);
        return ResultUtils.success(user.getId());
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody User user) throws Exception {
        if (user == null) {
            throw new Exception();
        }
        return ResultUtils.success(userService.updateById(user));
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) throws Exception {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new Exception();
        }
        return ResultUtils.success(userService.removeById(deleteRequest.getId()));
    }
}
