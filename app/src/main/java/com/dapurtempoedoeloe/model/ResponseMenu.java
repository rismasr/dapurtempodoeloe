package com.dapurtempoedoeloe.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMenu {

    @SerializedName("menu")
    private List<MenuItem> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<MenuItem> menu){
        this.menu = menu;
    }

    public List<MenuItem> getMenu(){
        return menu;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "ResponseMenu{" +
                        "menu = '" + menu + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}

