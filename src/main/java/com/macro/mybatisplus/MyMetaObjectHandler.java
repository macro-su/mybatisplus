package com.macro.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createtime = this.getFieldValByName("createtime",metaObject);
        if (null == createtime) {
            metaObject.setValue("createtime", new Date().toString());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        insertFill(metaObject);
    }
}
