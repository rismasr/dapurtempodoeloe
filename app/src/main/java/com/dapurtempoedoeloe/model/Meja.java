package com.dapurtempoedoeloe.model;

public class Meja extends BaseResponse {

    private MejaData data;

    public Meja() {
    }

    public MejaData getData() {
        return data;
    }

    public void setData(MejaData data) {
        this.data = data;
    }
}
