package com.dapurtempoedoeloe.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ListDataPesanan {


    @SerializedName("pesanan")
    @Expose
    private ArrayList<PesananModel> pesanan = new ArrayList<>();

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public ArrayList<PesananModel> getPesanan() {
        return pesanan;
    }

    public void setPesanan(ArrayList<PesananModel> pesanan) {
        this.pesanan = pesanan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
