package com.ripon.adminserver.dto;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private static final String SUCCESS="success";
    private static final String FAIL="fail";
    private String status;
    private String message;
    private Object data;

    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static Response success() {
        return new Response(SUCCESS);
    }

    public static Response success(Object data) {
        return new Response(SUCCESS, data);
    }

    public static Response fail(String message) {
        return new Response(FAIL, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
