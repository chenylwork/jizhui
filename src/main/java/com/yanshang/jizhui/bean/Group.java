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
    private String groupid;
    private String name;
    private String descr;
    private String ownerusername;
    private Integer maxmembercount;
    private String ctime;
    private String mtime;
    private String avatar;


}
