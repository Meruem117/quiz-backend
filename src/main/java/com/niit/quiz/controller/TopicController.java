package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.niit.quiz.base.exception.BaseException;
import com.niit.quiz.base.exception.ErrorCodeEnum;
import com.niit.quiz.base.request.DeleteRequest;
import com.niit.quiz.base.request.PageRequest;
import com.niit.quiz.base.response.BaseResponse;
import com.niit.quiz.utils.DateUtils;
import com.niit.quiz.utils.ResultUtils;
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
     * get topics with pagination
     *
     * @param pageRequest page request
     * @return topic item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Topic>> getTopicPages(PageRequest pageRequest) {
        int page = pageRequest.getPage();
        int size = pageRequest.getSize();
        if (page < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Topic> topicPage = new Page<>(page, size);
        return ResultUtils.success(topicService.page(topicPage));
    }

    /**
     * add topic
     *
     * @param topic topic item
     * @return topic id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addTopic(@RequestBody Topic topic) {
        if (topic == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String date = DateUtils.getCurrentDate();
        topic.setCreateTime(date);
        topicService.save(topic);
        return ResultUtils.success(topic.getId());
    }

    /**
     * update topic
     *
     * @param topic topic item
     * @return update status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateTopic(@RequestBody Topic topic) {
        if (topic == null) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(topicService.updateById(topic));
    }

    /**
     * logical delete topic
     *
     * @param deleteRequest topic id
     * @return delete status
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> deleteTopic(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(topicService.removeById(deleteRequest.getId()));
    }
}
