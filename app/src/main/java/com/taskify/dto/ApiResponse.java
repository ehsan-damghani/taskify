package com.taskify.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String primaryMessage;
    private String secondaryMessage;
    private Map<String, Object> payload;
    private List<String> errors;

    public ApiResponse(boolean success, int code, String primaryMessage, String secondaryMessage, Map<String, Object> payload, List<String> errors) {
        this.success = success;
        this.code = code;
        this.primaryMessage = primaryMessage;
        this.secondaryMessage = secondaryMessage;
        this.payload = payload;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getPrimaryMessage() {
        return primaryMessage;
    }

    public String getSecondaryMessage() {
        return secondaryMessage;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public List<String> getErrors() {
        return errors;
    }

    public static <T> ApiResponse<T> success(Map<String, Object> payload) {
        return new ApiResponse<>(true, 200, "OK", "موفق", payload, new ArrayList<>());
    }

    public static <T> ApiResponse<T> error(int code, String primaryMessage, String secondaryMessage, List<String> errors) {
        return new ApiResponse<>(false, code, primaryMessage, secondaryMessage, new HashMap<>(), errors);
    }
}
