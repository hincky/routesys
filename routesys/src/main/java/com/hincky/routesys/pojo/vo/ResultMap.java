package com.hincky.routesys.pojo.vo;

import lombok.Data;

@Data
public class ResultMap<T> {
    private String msg;
    private  T data;
    private  int code;
    private  int count;

    public ResultMap(String msg, T data, int code, int count) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }

    public ResultMap() {
    }
}
