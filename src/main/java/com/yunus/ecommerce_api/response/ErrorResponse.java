package com.yunus.ecommerce_api.response;

import java.util.List;

public class ErrorResponse {

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.error = true;
        this.details = details;
    }

    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        this.message = message;
        this.error = true;
    }

    private Boolean error;
    private String message;
    private List<String> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = true;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }


}
