package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.quiz.base.request.LoginRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.PasswordCheckResponse;
import com.niit.quiz.model.entity.Admin;
import com.niit.quiz.service.AdminService;
import com.niit.quiz.utils.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * check admin password
     *
     * @param request email & password
     * @return check result
     */
    @PostMapping("check")
    public BaseResponse<PasswordCheckResponse> checkAdminPassword(@RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("email", email);
        Admin admin = adminService.getOne(adminQueryWrapper);
        Boolean check = Objects.equals(password, admin.getPassword());
        return ResultUtils.success(new PasswordCheckResponse(check, null));
    }
}
