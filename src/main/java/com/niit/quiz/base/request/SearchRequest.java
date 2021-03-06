package com.niit.quiz.base.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest {
    private String key;
}
