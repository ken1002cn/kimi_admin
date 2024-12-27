package com.kimi.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * 如果不给result指定泛型 会自动指定为object类型
 * 如果该返回值无需返回data项，那么指定不指定泛型都没有区别
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //状态码
    private String msg; //消息
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 200;
        return result;
    }

    /**
     * 使用工厂方法的时候传入泛型参数，工厂方法生成出来的对象就传入了泛型变量
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 200;
        return result;
    }

    public static <T> Result<T> error(String msg,Integer code) {
        Result result = new Result();
        result.msg = msg;
        result.code = code;
        return result;
    }

}
