package com.zhangle.produce.domain.echarts;


/**
 * @description:
 * @author: zl52074
 * @time: 2020/4/15 23:30
 */
public class LineData {
    private String name;
    private String type = "line";
    private String[] data;
    private String color;
    private boolean smooth = true;
    private boolean connectNulls = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSmooth() {
        return smooth;
    }

    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }

    public boolean isConnectNulls() {
        return connectNulls;
    }

    public void setConnectNulls(boolean connectNulls) {
        this.connectNulls = connectNulls;
    }
}
