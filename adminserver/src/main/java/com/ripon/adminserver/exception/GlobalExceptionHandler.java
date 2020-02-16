package com.ripon.adminserver.exception;

import com.ripon.adminserver.dto.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e){
        String message=e.getMessage();
        if(message==null||message.equals("")){
            message="服务器出错";
        }
        e.printStackTrace();
        return Response.fail(message);
    }
}
