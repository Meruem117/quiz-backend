package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.niit.quiz.base.request.LoginRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.CheckInfo;
import com.niit.quiz.base.response.CheckResponse;
import com.niit.quiz.model.entity.Admin;
import com.niit.quiz.service.AdminService;
import com.niit.quiz.utils.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @PostMapping("/check")
    public BaseResponse<CheckResponse> checkAdminPassword(@RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("email", email);
        adminQueryWrapper.eq("password", password);
        Admin admin = adminService.getOne(adminQueryWrapper);
        if (ObjectUtils.isNotNull(admin)) {
            CheckInfo info = new CheckInfo(admin.getId(), admin.getName(), null, null);
            return ResultUtils.success(new CheckResponse(true, info));
        } else {
            return ResultUtils.error("wrong email or password");
        }
    }
}
