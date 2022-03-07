package com.dapurtempoedoeloe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dapurtempoedoeloe.R;
import com.dapurtempoedoeloe.model.PesananModel;

import java.util.ArrayList;

/**
 * Created by smartos on 19/07/19.
 */

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.MyViewHolder> {
    private Context context;
    private ArrayList<PesananModel> listData;


    public AdapterPesanan(Context context, ArrayList<PesananModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public AdapterPesanan.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pesanan, parent, false);
        return new AdapterPesanan.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterPesanan.MyViewHolder holder, final int position) {

        holder.tvNamaMenu.setText(listData.get(position).getNama());
        holder.tvJumlah.setText("Jumlah Pesanan : "+ listData.get(position).getJumlah());

        int status = listData.get(position).getStatus();

        if(status == 0){
            holder.tvStatus.setText("Status : Menunggu");
        }else if(status == 1){
            holder.tvStatus.setText("Status : Sedang Di Proses");
        }else if(status == 3) {
            holder.tvStatus.setText("Status : Siap Di Hidangkan");
        }else if(status == 4) {
            holder.tvStatus.setText("Status : Pesanan Dibatalkan");
        }
    }

    @Override
    public int getItemCount() {

        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaMenu, tvJumlah, tvStatus;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNamaMenu = (TextView) itemView.findViewById(R.id.namaMenu);
            tvJumlah = (TextView) itemView.findViewById(R.id.jumlah);
            tvStatus = (TextView) itemView.findViewById(R.id.status);

        }
    }
}

