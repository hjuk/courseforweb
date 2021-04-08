package com.guli.utils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果
@Data
public class R {
    private Boolean success ;

    private Integer code;

    private String massage;

    private Map<String,Object> data = new HashMap<String,Object>();


    private R(){}

    public static R success(){
        R r=new R();
        r.setSuccess(true);
        r.setCode(RCode.SUCCESS);
        r.setMassage("success");
        return r;
    }

    public static R failed(){
        R r=new R();
        r.setSuccess(false);
        r.setCode(RCode.ERROR);
        r.setMassage("false");
        return r;
    }
    public R success(Boolean success) {
     this.setSuccess(success);
     return this;
    }
    public R massage(String message){
        this.setMassage(message);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }




}
