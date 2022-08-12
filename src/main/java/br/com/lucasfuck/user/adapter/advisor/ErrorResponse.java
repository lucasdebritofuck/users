package br.com.lucasfuck.user.adapter.advisor;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(LocalDateTime timestamp, int status,
                         String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
