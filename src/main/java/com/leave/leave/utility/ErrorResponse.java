package com.leave.leave.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ErrorResponse {
    private String status;
    private String errorCode;
    private String errorDescription;


    public static ResponseEntity<Map<String, Object>> buildErrorResponse(String errorCode) {
        return buildErrorResponse(errorCode, ErrorCodes.getErrorDescription(errorCode));
    }

    public static ResponseEntity<Map<String, Object>> buildErrorResponse(String errorCode, String customMessage) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("errorCode", errorCode);
        errorBody.put("message", customMessage);
        errorBody.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
