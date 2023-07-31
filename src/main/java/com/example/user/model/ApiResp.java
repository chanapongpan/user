package com.example.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResp<T> {
    private boolean success;
    private T data;

    @Schema(hidden = true)
    private Error error;

    public static <T> ApiResp<T> success(T data) {
        return new ApiResp<>(true, data, null);
    }

    public static <T> ApiResp<T> error(ErrorCode code, String message) {
        Error errorResp = new Error(code, message);
        return new ApiResp<>(false, null, errorResp);
    }
}
