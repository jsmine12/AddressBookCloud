package com.AddressBookClient.pojo;
import com.alibaba.fastjson.annotation.JSONField;
public class Result {
    @JSONField(name="code",ordinal = 1)
    short code;
    @JSONField(name="msg",ordinal = 2)
    String msg;
    @JSONField(name = "data",ordinal = 3)
    Object data;
    Result(){}
    Result(Short code, String msg, Object data){
        Class a=Result.class;
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public static Result Success(Object data){
        return new Result((short)1,"success",data);
    }
    public static Result Success(String msg,Object data){
        return new Result((short)1,msg,data);
    }
    public static Result Error(Object data){
        return new Result((short)0,"error",data);
    }
    public short getCode() {
        return code;
    }
    public void setCode(short code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
