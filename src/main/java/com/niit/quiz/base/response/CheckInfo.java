package com.niit.quiz.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckInfo {
    private Integer id;
    private String name;
    private Integer gender;
    private String location;
}
