package com.leave.leave.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {
    private String status;
    private T message;

    public SuccessResponse(String status, T message) {
        this.status = status;
        this.message = message;
    }

}
