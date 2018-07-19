package com.kunlunsoft.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class DescriptionDto {
    private Boolean isComplexColumn;
    private String desc2;
    private Map<String, DescriptionDto> children;
}
