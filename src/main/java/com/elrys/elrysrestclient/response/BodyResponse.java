package com.elrys.elrysrestclient.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodyResponse<T> {
    private String status;
    private T data;
    private String message;
}
