package com.locotor.kanpm.web.common;

import com.locotor.kanpm.model.enums.ResponseCode;
import com.locotor.kanpm.model.payloads.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = CommonResponse.class)
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e){
        return ResponseData.build(ResponseCode.FAIL);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseData handleRuntimeException(RuntimeException e){
        return ResponseData.build(ResponseCode.SYS_ERROR);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseData handleBaseException(CommonException e){
        ResponseCode code = e.getCode();
        return ResponseData.build(code);
    }

}
