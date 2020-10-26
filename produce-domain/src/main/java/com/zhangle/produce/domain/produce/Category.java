package com.zhangle.produce.domain.produce;

import java.io.Serializable;

/**
 * @description: 农产品分类
 * @author: zl52074
 * @time: 2020/3/25 15:41
 */
public class Category implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
