package com.kunlunsoft.dto;

import com.kunlunsoft.model2.Interface;
import com.kunlunsoft.model2.response.ResponseArgsItem;
import lombok.Data;

import java.util.List;

@Data
public class ResponseArgsDto extends Interface {
    private List<ResponseArgsItem> responseargItems;

}
