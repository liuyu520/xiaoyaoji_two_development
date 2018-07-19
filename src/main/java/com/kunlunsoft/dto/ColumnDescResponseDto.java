package com.kunlunsoft.dto;

import com.common.bean.BaseResponseDto;
import lombok.Data;

import java.util.Map;

@Data
public class ColumnDescResponseDto extends BaseResponseDto {
    Map<String, String> value;

}
