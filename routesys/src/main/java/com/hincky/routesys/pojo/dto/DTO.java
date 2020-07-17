package com.hincky.routesys.pojo.dto;

import lombok.Data;

@Data
public class DTO {
    public static final String Success = "success";
    public static final String Fail = "fail";

    private String resultCode;
    private String resultMessage;
    private Object resultObj;
    private Object param;
    private Page page;
}
