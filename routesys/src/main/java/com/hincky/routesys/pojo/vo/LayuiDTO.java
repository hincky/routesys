package com.hincky.routesys.pojo.vo;

import lombok.Data;

import java.util.List;


@Data
public class LayuiDTO<T> {
    private int code;
    private String msg;
    private int count;
    private List<T> data;

    public LayuiDTO(int code, String msg, int count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
