package com.niit.quiz.base.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 3502259215648936396L;

    private int pageNum = 1;
    private int pageSize = 10;
}
