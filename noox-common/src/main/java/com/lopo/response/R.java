package com.lopo.response;

import org.springframework.http.HttpStatus;

public class R {
    private Integer code;
    private String msg;
    private Object data;

    public static R success(){
        R r = new R();
        r.setCode(HttpStatus.OK.value()).setMsg("success").setData("");
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public R setData(Object data) {
        this.data = data;
        return this;
    }
}
