package ru.otus.shurupov.spring.actuator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BreadCrumb {
    private String url;
    private String caption;
}
