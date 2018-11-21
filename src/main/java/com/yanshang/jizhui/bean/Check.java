package com.yanshang.jizhui.bean;/*
 *
 * @ClassName Check
 * @Description 脊椎检查结果表
 * @Author 陈彦磊
 * @Date 2018年11月21日13:00:16
 * @Version 1.0
 *
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "t_check")
@Entity
@Data
@ToString
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sensors; // 检查的脊椎对应节的检查编号：1151126411
    private String time; // 检查时间
    private String result; // 检查结果
    private String username; // 检查用户
    public Check() {}
    public Check(String sensors,String time,String result,String username) {
        super();
        this.sensors = sensors;
        this.time = time;
        this.result = result;
        this.username = username;
    }
}