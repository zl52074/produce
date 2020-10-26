package com.zhangle.produce.domain.datatables;

import java.util.List;

/**
 * @description: datatable分页bean
 * @author: zl52074
 * @time: 2020/1/2 13:55
 */
public class DataTables<E> {

    private int recordsTotal ;
    private int recordsFiltered;
    private int draw;
    private List<E> data ;

    //分页信息如下四个属性：

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

}
