package com.zhangle.produce.domain.produce;

import java.io.Serializable;

/**
 * @description: 农产品种类
 * @author: zl52074
 * @time: 2020/3/25 17:59
 */
public class Produce implements Serializable {
    private String id;
    private String name;
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Produce{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
