package com.yanshang.jizhui.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "t_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String state; // 使用状态
    private String SensorID01;
    private String SensorID02;
    private String SensorID03;
    private String SensorID04;
    private String SensorID05;
    private String SensorID06;
    private String SensorID07;
    private String SensorID08;
    private String SensorID09;
    private String SensorID10;
    @SuppressWarnings("unused")
    private String result;
    private String username; // 账号
    private String createtime; // 创建时间

    public String getResult() {
        String result = this.SensorID01 + this.SensorID02 + this.SensorID03 + this.SensorID04 +
                this.SensorID05 + this.SensorID06 + this.SensorID07 + this.SensorID08
                + this.SensorID09 + this.SensorID10;
        //System.out.println(result);
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
