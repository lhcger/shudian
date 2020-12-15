package com.example.demo.api.model;

import com.example.demo.api.enums.ValidFlagEnum;
import lombok.Data;

import java.util.Date;

/**
 * 实体类的父类：用来存放共有的表数据
 * @author superman
 */
@Data
public class BaseDTO {
    /**
     * 数据ID*
     **/
    private Integer id;

    /**
     * 临时字段1
     */
    private String temp1;

    /**
     *临时字段2
     */
    private String temp2;

    /**
     * 入库时间
     */
    private Date createDate;

    /**
     * 最后一次修改的时间
     */
    private Date updateDate;

    /**
     * 数据是否有效
     */
    private ValidFlagEnum validFlag;
}
