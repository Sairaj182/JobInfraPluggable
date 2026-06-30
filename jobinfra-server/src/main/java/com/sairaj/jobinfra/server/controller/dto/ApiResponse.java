package com.sairaj.jobinfra.server.controller.dto;

import java.time.Instant;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;
    private Instant timestamp;
    private String requestId;

    public ApiResponse() {
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTimestamp(Instant.now());
        response.setRequestId(org.slf4j.MDC.get("requestId"));
        return response;
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setError(new ApiError(code, message));
        response.setTimestamp(Instant.now());
        response.setRequestId(org.slf4j.MDC.get("requestId"));
        return response;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public ApiError getError() { return error; }
    public void setError(ApiError error) { this.error = error; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public static class ApiError {
        private String code;
        private String message;

        public ApiError(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
