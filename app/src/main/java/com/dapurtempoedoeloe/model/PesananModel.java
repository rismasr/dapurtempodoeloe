package com.dapurtempoedoeloe.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PesananModel {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("id_meja") private int id_meja;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("harga") private int harga;
    @Expose
    @SerializedName("no_meja") private int no_meja;
    @Expose
    @SerializedName("jumlah") private int jumlah;
    @Expose
    @SerializedName("status") private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_meja() {
        return id_meja;
    }

    public void setId_meja(int id_meja) {
        this.id_meja = id_meja;
    }

    public  String getNama(){
        return  nama;
    }
    public void  setNama(String nama){
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getNo_meja() {
        return no_meja;
    }

    public void setNo_meja(int no_meja) {
        this.no_meja = no_meja;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getStatus(){
        return  status;
    }
    public void setStatus(int status){
        this.status = status;
    }
}

