package com.spring.security.dto;

import com.spring.security.common.DateUtilLocal;

import javax.persistence.Convert;
import java.time.LocalDateTime;

public class ErrorResponseDto {

    private int status;

    private String message;

    @Convert(converter = DateUtilLocal.class)
    private LocalDateTime timestamp;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
