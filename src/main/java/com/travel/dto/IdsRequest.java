package com.travel.dto;

import java.util.List;

public class IdsRequest {

    private List<Long> ids;

    public IdsRequest() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}