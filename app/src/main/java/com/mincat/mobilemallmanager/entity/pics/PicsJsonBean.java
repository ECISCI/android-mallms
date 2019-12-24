package com.mincat.mobilemallmanager.entity.pics;

import com.mincat.mobilemallmanager.bean.BaseBean;

import java.util.List;

public class PicsJsonBean extends BaseBean {

    private List<PicsRes> data;

    public List<PicsRes> getData() {
        return data;
    }

    public void setData(List<PicsRes> data) {
        this.data = data;
    }
}
