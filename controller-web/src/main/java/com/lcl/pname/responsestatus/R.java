package com.lcl.pname.responsestatus;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author xlz
 * @date 2022/2/26 9:24
 * 返回值类型的固定成员：
 *    Integer  code   记录当前的状态码     0代表成功    1代表错误    2....
 *    String  message  要返回的简单的字符串信息
 *    Object  data   其他的复杂数据类型
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    // 屏蔽默认构造函数，让外界不不能直接创建对象
    private R(){}

    public static<T> R<T> ok(){
        final R<T> r = new R<>();
        r.code = ResultCode.SUCCESS.code;
        r.message = ResultCode.SUCCESS.message;
        return r;
    }

    public static<T> R<T> ok(T data){
        final R<T> r = new R<>();
        r.code = ResultCode.SUCCESS.code;
        r.message = ResultCode.SUCCESS.message;
        r.data = data;
        return r;
    }

    public static<T> R<T> ok(ResultCode resultCode, T data){
        final R<T> r = new R<>();
        r.code = resultCode.code;
        r.message = resultCode.message;
        r.data = data;
        return r;
    }

    public static<T> R<T> ok(String message, T data){
        final R<T> r = new R<>();
        r.code = ResultCode.SUCCESS.code;
        r.message = message;
        r.data = data;
        return r;
    }

    public static<T> R<T> error(){
        final R<T> r = new R<>();
        r.code = ResultCode.UNKNOWN.code;
        r.message = ResultCode.UNKNOWN.message;
        return r;
    }

    public static<T> R<T> error(ResultCode resultCode){
        final R<T> r = new R<>();
        r.code = resultCode.code;
        r.message = resultCode.message;
        return r;
    }

    public static<T> R<T> error(ResultCode resultCode,T data){
        final R<T> r = new R<>();
        r.code = resultCode.code;
        r.message = resultCode.message;
        r.data = data;
        return r;
    }

    //public Integer getCode() {
    //    return code;
    //}
    //
    //
    //public String getMessage() {
    //    return message;
    //}
    //
    //
    //public T getData() {
    //    return data;
    //}
    //
    //
    //public R<T> setCode(Integer code) {
    //    this.code = code;
    //    return this;
    //}
    //
    //public R<T> setMessage(String message) {
    //    this.message = message;
    //    return this;
    //}
    //
    //public R<T> setData(T data) {
    //    this.data = data;
    //    return this;
    //}
}
