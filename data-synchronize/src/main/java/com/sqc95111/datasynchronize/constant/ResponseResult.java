package com.sqc95111.datasynchronize.constant;

import lombok.Data;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2018/12/25 11:08
 * @Version 1.0
 */
@Data
public class ResponseResult {

    private Integer code;
    private String message;
    private Boolean success;
    private Object data;

    public static ResponseResult build(){
        return new ResponseResult();
    }

    public ResponseResult success(){
        this.code = 500;
        this.success = true;
        this.message = "操作成功";
        return this;
    }

    public ResponseResult success(Object data){
        this.code = 500;
        this.success = true;
        this.data = data;
        this.message = "操作成功";
        return this;
    }

    public ResponseResult success(String message){
        this.code = 500;
        this.success = true;
        this.message = message;
        return this;
    }

    public ResponseResult fail(){
        this.code = 400;
        this.success = false;
        this.message = "操作失败";
        return this;
    }

    public ResponseResult data(Object data){
        this.data = data;
        return this;
    }

    public ResponseResult message(String message){
        this.message = message;
        return this;
    }

    public ResponseResult code(Integer code){
        this.code = code;
        return this;
    }

    public ResponseResult(Integer code, String message, Boolean success, Object data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public ResponseResult() {
    }
}
