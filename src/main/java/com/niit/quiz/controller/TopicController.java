package com.niit.quiz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
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
     * get topic with pagination
     *
     * @param pageRequest page request
     * @return topic item list with pagination
     */
    @GetMapping("/page")
    public BaseResponse<IPage<Topic>> getTopicPages(PageRequest pageRequest) {
        Integer page = pageRequest.getPage();
        Integer size = pageRequest.getSize();
        if (page < 1 || size < 0) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        Page<Topic> topicPage = new Page<>(page, size);
        QueryWrapper<Topic> topicQueryWrapper = new QueryWrapper<>();
        return ResultUtils.success(topicService.page(topicPage, topicQueryWrapper));
    }

    /**
     * add topic
     *
     * @param topic topic item
     * @return topic id
     */
    @PostMapping("/add")
    public BaseResponse<Integer> addTopic(@RequestBody Topic topic) {
        if (ObjectUtils.isNull(topic)) {
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
        if (ObjectUtils.isNull(topic)) {
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
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTopic(@RequestBody DeleteRequest deleteRequest) {
        if (ObjectUtils.isNull(deleteRequest) || deleteRequest.getId() < 1) {
            throw new BaseException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        return ResultUtils.success(topicService.removeById(deleteRequest.getId()));
    }
}
