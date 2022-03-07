package com.dapurtempoedoeloe.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dapurtempoedoeloe.DetailMenu;
import com.dapurtempoedoeloe.R;
import com.dapurtempoedoeloe.config.Rest;
import com.dapurtempoedoeloe.model.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MyViewHolder> {
    Context context;
    List<MenuItem> menu;
    public AdapterMenu(Context context, List<MenuItem> data_menu) {
        // Inisialisasi
        this.context = context;
        this.menu = data_menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvNama.setText(menu.get(position).getNama());
        holder.tvHarga.setText( "Rp. " + menu.get(position).getHarga());
        holder.tvStok.setText( "Stok : " + menu.get(position).getStok());
        // Dapatkan url gambar
        final String urlGambarMenu = Rest.GAMBAR + menu.get(position).getGambar();
        // Set image ke widget dengaa menggunakan Library Glide
        // krena imagenya dari internet
        Glide.with(context)
                .load(urlGambarMenu)
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .into(holder.ivGambarMenu);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailMenu.class);
                Bundle data = new Bundle();
                data.putInt("ID", menu.get(position).getId());
                data.putInt("ID_KATEGORI", menu.get(position).getId_kategori());
                data.putString("NAMA", menu.get(position).getNama());
                data.putInt("HARGA", menu.get(position).getHarga());
                data.putString("DESKRIPSI", menu.get(position).getDeskripsi());
                data.putInt("STOK", menu.get(position).getStok());
                data.putString("GAMBAR", urlGambarMenu);
                varIntent.putExtras(data);
                context.startActivity(varIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarMenu;
        TextView tvNama;
        TextView tvHarga;
        TextView tvStok;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivGambarMenu = (ImageView) itemView.findViewById(R.id.gambar);
            tvNama = (TextView) itemView.findViewById(R.id.nama);
            tvHarga = (TextView) itemView.findViewById(R.id.harga);
            tvStok = (TextView) itemView.findViewById(R.id.stok);
        }
    }
}