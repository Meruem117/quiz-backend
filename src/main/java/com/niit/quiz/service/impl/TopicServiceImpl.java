package com.niit.quiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.quiz.model.entity.Topic;
import com.niit.quiz.mapper.TopicMapper;
import com.niit.quiz.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
}
