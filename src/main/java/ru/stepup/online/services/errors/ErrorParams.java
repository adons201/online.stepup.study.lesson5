package ru.stepup.online.services.errors;

import org.springframework.http.HttpStatus;

public class ErrorParams {
    private String param;
    private HttpStatus httpStatus;

    public ErrorParams(String param, HttpStatus httpStatus) {
        this.param = param;
        this.httpStatus = httpStatus;
    }

    public String getParam() {
        return param;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
