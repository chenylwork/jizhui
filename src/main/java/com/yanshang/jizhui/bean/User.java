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
    private String username;
    private String password;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String nickname;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String sex;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private Integer age;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String image;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String phone;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String userid;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String token;
    @JsonSerialize(include = Inclusion.NON_EMPTY)
    private String rolename;
}
