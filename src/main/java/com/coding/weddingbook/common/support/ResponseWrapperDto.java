package com.coding.weddingbook.common.support;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseWrapperDto {
    private String code;
    private String message;
    private List list;
    private Pagination pagination;
}
