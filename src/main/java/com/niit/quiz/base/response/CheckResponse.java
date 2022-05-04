package com.niit.quiz.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckResponse {
    private Boolean check;
    private CheckInfo info;
}
