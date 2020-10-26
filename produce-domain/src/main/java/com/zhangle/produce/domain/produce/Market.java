package com.zhangle.produce.domain.produce;

import java.io.Serializable;

/**
 * @description: 农产品市场
 * @author: zl52074
 * @time: 2020/3/21 18:23
 */
public class Market implements Serializable {
    private String id; //市场id
    private String name;  //市场名称
    private String info; //市场详情
    private int type; //销售类别
    private String address; //地址
    private String province; //省份

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equals("销地")){
            this.type=0;
        }else if(type.equals("产地")){
            this.type=1;
        }else
            this.type=9;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", type=" + type +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
