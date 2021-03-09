package com.locotor.kanpm.model.payloads;

import com.locotor.kanpm.model.enums.ResponseCode;

public class ResponseData {

    private String code = null;

    private String message = null;

    private Object data;

    public ResponseData() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
        this.data = null;
    }

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResponseData(ResponseCode message, Object data) {
        this.code = message.getCode();
        this.message = message.getMessage();
        this.data = data;
    }

    public ResponseData(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData(Object data) {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
        this.data = data;
    }

    public static ResponseData build(ResponseCode message, Object data) {
        return new ResponseData(message, data);
    }

    public static ResponseData build(String code, String message, Object data) {
        return new ResponseData(code, message, data);
    }

    public static ResponseData build(ResponseCode message) {
        return new ResponseData(message, null);
    }

    public static ResponseData build(String code, String message) {
        return new ResponseData(code, message, null);
    }

    public static ResponseData ok(Object data) {
        return new ResponseData(data);
    }

    /**
     * 将返回数据设置为系统错误状态
     */
    public void change2SysError() {
        this.code = ResponseCode.SYS_ERROR.getCode();
        this.message = ResponseCode.SYS_ERROR.getMessage();
    }

    public void fail() {
        this.code = ResponseCode.FAIL.getCode();
        this.message = ResponseCode.FAIL.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setResponseCode(ResponseCode ResponseCode) {
        this.code = ResponseCode.getCode();
        this.message = ResponseCode.getMessage();
    }

    public void setCode(String code) {
        this.code = code;
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
