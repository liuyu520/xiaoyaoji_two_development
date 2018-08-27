package com.kunlunsoft.dto.request;

import lombok.Data;

@Data
public class UpdateAagsParam {
    private Boolean forceCover;
    /***
     * 比如parentNodeName 值为"houseDetail"时,只更新节点 houseDetail 下的叶子节点
     */
    private String parentNodeName;
}
