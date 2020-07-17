package com.hincky.routesys.pojo.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * 返回前端页面的工具类
 */
@Data
public class JsonData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code; // 状态码 0 表示成功，1表示处理中，-1表示失败
    private String msg;// 描述
    private Integer count;
    private Object data; // 数据


    public JsonData() {
    }

    public JsonData(Integer code,Integer count, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    // 成功，传入数据
    public static JsonData buildSuccess() {
        return new JsonData(0,0 ,null, null);
    }

    // 成功，传入数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0,0 ,"Success", data);
    }

    // 失败，传入描述信息
    public static JsonData buildError(String msg) {
        return new JsonData(-1, 0, msg,null);
    }

    // 失败，传入描述信息,状态码
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code,0,msg,null);
    }

    // 成功，传入数据,及描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(0,0,msg,data);
    }

    // 成功，传入数据,及状态码
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code,0,"Success",data);
    }

    @Override
    public String toString() {
        return "JsonData [code=" + code + ", msg=" + msg + ",count=" + count + ", data=" + data
                + "]";
    }
}
