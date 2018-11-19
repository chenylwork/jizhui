package com.yanshang.jizhui.util;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * 返回结果
 * @author Administrator
 *
 * @param <T>
 */
public class ResultString<T> {
    private Integer code;
    private T data;
    private String message;


}
