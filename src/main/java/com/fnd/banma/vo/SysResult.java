package com.fnd.banma.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义响应结构
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysResult<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    /*
     * 200	成功
     * 201	错误
     * 400	参数错误
     */
    private Integer status;

    //
    private String appCode;

    //编码
    private int code;

    // 响应消息
    private String msg;

    // 响应中的数据
    private T data;

    public static SysResult build(Integer status, String msg, Object data) {
        return new SysResult(status, msg, data);
    }

    public static SysResult oK(Object data) {
        return new SysResult(data);
    }

    public static SysResult oK() {
        return new SysResult(null);
    }

    public static SysResult genSuccessResult(Object data){
        SysResult sysResult = new SysResult();
        sysResult.setStatus(200);
        sysResult.setMsg("success");
        sysResult.setData(data);
        return sysResult;
    }

    public static SysResult genErrorResult(){
        SysResult sysResult = new SysResult();
        sysResult.setStatus(201);
        sysResult.setMsg("error");
        return sysResult;
    }

    public static SysResult genErrorResult(String appCode,int code,String message,String error){
        SysResult sysResult = new SysResult();

        sysResult.setCode(code);
        sysResult.setStatus(201);
        sysResult.setMsg(message);

        return sysResult;
    }

    public static SysResult genErrorResult(String message){
        SysResult sysResult = new SysResult();

        sysResult.setStatus(201);
        sysResult.setMsg(message);

        return sysResult;
    }

    public static SysResult genDefeated(String message){
        SysResult sysResult = new SysResult();

        sysResult.setStatus(400);
        sysResult.setMsg(message);

        return sysResult;
    }

    public SysResult() {

    }

    public static SysResult build(Integer status, String msg) {
        return new SysResult(status, msg, null);
    }

    public SysResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SysResult(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    public Boolean isOk() {
        return this.status == 200;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为SysResult对象
     *
     * @param jsonData json数据
     * @param clazz SysResult中的object类型
     * @return
     */
    public static SysResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, SysResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static SysResult format(String json) {
        try {
            return MAPPER.readValue(json, SysResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static SysResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}