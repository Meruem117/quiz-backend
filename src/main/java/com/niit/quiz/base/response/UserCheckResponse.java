package com.niit.quiz.base.response;

import com.niit.quiz.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCheckResponse {
    private Boolean check;
    private User userInfo;
}
