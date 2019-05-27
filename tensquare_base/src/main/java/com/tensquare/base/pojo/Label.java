package com.tensquare.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 标签实体类
 *
 * @author waver
 * @date 2019.5.27 10:14
 */

@Entity
@Table(name = "tb_label")
@Data
public class Label {
    @Id
    private String id;
    /**
     * 标签名称
     */
    private String labelName;
    /**
     * 状态
     */
    private String state;
    /**
     * 使用数量
     */
    private Long count;
    /**
     * 关注数
     */
    private Long fans;
    /**
     * 是否推荐
     */
    private String recommend;
}
