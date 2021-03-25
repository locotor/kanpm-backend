package com.locotor.kanpm.web.common;

import com.locotor.kanpm.model.enums.ResponseCode;
import lombok.Data;

@Data
public class CommonException extends RuntimeException{

    private ResponseCode code;

    public CommonException(ResponseCode code){
        this.code = code;
    }

    public CommonException(Throwable cause,ResponseCode code){
        super(cause);
        this.code = code;
    }

}
