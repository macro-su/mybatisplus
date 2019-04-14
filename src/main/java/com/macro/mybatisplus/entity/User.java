package com.macro.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
public class User extends Model<User> {
    @TableId(value="id",type = IdType.AUTO)//指定主键，并说明主键增长策略
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleteFlag;//逻辑删除

    @TableField(value = "createtime",fill = FieldFill.INSERT_UPDATE)//公共字段自动填充
    private String createtime;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }//继承Model类并重写获取主键的方法pkVal() 实现AR领域驱动
}