package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.PageRequest;
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

    /**
     * get topic pages
     *
     * @return topic item pages
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Topic>> getTopicPages(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        if (pageNum < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Topic> topicPage = new Page<>(pageNum, pageSize);
        return ResultUtils.success(topicService.page(topicPage));
    }
}
