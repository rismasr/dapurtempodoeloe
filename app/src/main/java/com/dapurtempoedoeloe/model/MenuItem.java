package com.dapurtempoedoeloe.model;

import com.google.gson.annotations.SerializedName;

public class MenuItem{

    @SerializedName("id")
    private int id;

    @SerializedName("id_kategori")
    private int id_kategori;

    @SerializedName("nama")
    private String nama;

    @SerializedName("harga")
    private int harga;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("stok")
    private int stok;

    @SerializedName("gambar")
    private String gambar;

    public void setId(Integer id){this.id = id;}

    public int getId(){return id;}

    public void setId_kategori(Integer id_kategori){this.id_kategori = id_kategori;}

    public int getId_kategori(){return id_kategori;}

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setHarga(Integer harga){
        this.harga = harga;
    }

    public int getHarga(){
        return harga;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setStok(Integer stok){
        this.stok = stok;
    }

    public int getStok(){
        return stok;
    }

    public void setGambar(String gambar){
        this.gambar = gambar;
    }

    public String getGambar(){
        return gambar;
    }

    @Override
    public String toString(){
        return
                "MenuItem{" +
                        "id = '" + id + '\'' +
                        ",id_kategori = '" + id_kategori + '\'' +
                        ",nama = '" + nama + '\'' +
                        ",harga = '" + harga + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",stok = '" + stok + '\'' +
                        ",gambar = '" + gambar + '\'' +
                        "}";
    }
}

