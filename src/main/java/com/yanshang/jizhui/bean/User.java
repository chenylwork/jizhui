package com.yanshang.jizhui.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "t_user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username; // 账号
    private String password; // 密码
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String nickname; // 昵称
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String sex; // 性别
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private Integer age; // 年龄
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String image; // 头像图片
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String phone; // 电话
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String userid; // 用户id
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String token; // token
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String rolename; // 角色名称
}
