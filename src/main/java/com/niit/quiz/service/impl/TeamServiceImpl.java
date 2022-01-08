package com.niit.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.quiz.entity.Team;
import com.niit.quiz.mapper.TeamMapper;
import com.niit.quiz.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
}
