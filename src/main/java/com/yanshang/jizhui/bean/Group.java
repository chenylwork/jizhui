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

/**
 * 群组实体类
 *
 * @author Administrator
 */
@Table(name = "t_group")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String groupid; // 分组编号
    private String name; // 分组名称
    private String descr; // 备注描述
    private String ownerusername; // 所有者用户名
    private Integer maxmembercount; // 最大成员数
    private String ctime; // 群创建时间
    private String mtime; // 最后一次修改时间
    private String avatar; // 群头像,mediaId
    private String url; // 群头像请求地址


}
