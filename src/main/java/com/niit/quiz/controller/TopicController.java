package com.niit.quiz.controller;

import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.base.response.ResultUtils;
import com.niit.quiz.model.entity.Topic;
import com.niit.quiz.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Resource
    private TopicService topicService;

    /**
     * get topic list
     *
     * @return topic item list
     */
    @GetMapping("/list")
    public BaseResponse<List<Topic>> getTopicList() {
        return ResultUtils.success(topicService.list());
    }
}
