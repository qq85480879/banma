package com.fnd.banma.vo;

public enum ErrorCode {

    QUERY_DETAIL_ERROR(1, "查询材料详情异常"),
    ;

    public static String appCode = "PM";
    public int code;
    public String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
