package com.elrys.elrysrestclient.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodyResponse<T> {
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private T data;

    private String message;
}
