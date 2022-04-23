package com.github.zzay.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zzay
 * @className ResponseBean
 * @description 响应Bean
 * @create 2022/04/21 14:06
 */
@Hidden
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseBean<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;

    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseBean(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
