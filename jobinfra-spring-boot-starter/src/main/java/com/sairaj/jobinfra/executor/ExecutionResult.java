package com.sairaj.jobinfra.executor;

public class ExecutionResult {
    private final boolean success;
    private final String errorMessage;
    private final int statusCode;
    private final long durationMs;
    private final String responseBody;

    public ExecutionResult(boolean success, String errorMessage, int statusCode, long durationMs, String responseBody) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.durationMs = durationMs;
        this.responseBody = responseBody;
    }

    public static ExecutionResult success(int statusCode, long durationMs, String responseBody) {
        return new ExecutionResult(true, null, statusCode, durationMs, responseBody);
    }

    public static ExecutionResult failure(String errorMessage, int statusCode, long durationMs, String responseBody) {
        return new ExecutionResult(false, errorMessage, statusCode, durationMs, responseBody);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
