package com.niit.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.quiz.mapper.ResultMapper;
import com.niit.quiz.model.entity.Result;
import com.niit.quiz.service.ResultService;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {
}
