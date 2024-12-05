package com.example.t2305m_wcd.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Subject {
    private Long id;
    private String name;
    private String code;
    private Long hour;
}
