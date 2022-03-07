package com.dapurtempoedoeloe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dapurtempoedoeloe.adapter.AdapterPesanan;
import com.dapurtempoedoeloe.config.ApiService;
import com.dapurtempoedoeloe.config.RetrofitMenu;
import com.dapurtempoedoeloe.model.ListDataPesanan;
import com.dapurtempoedoeloe.model.Meja;
import com.dapurtempoedoeloe.model.PesananModel;
import com.dapurtempoedoeloe.model.ResponseModelOrder;
import com.dapurtempoedoeloe.util.PrefUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.content.ContentValues.TAG;

public class Pesanan extends AppCompatActivity {
    ArrayList<PesananModel> listData;
    private RecyclerView recycler;
    Button checkout;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        Meja meja = PrefUtil.getMeja(this, PrefUtil.USER_SESSION);
        setTitle("Pesanan"+" "+"No Meja" + ":"+ meja.getData().getNo() + "");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listData = new ArrayList<>();

        ambilDataPesanan();

        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new AdapterPesanan(this, listData));

        checkout = (Button)findViewById(R.id.btnCheckout);

        pd = new ProgressDialog(this);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        Pesanan.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("CHECKOUT PESANAN");
                builder.setMessage("Apakah anda yakin ingin checkout pesanan?");
                builder.setNegativeButton("Tidak, gagalkan!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Pesanan.this,
                                        "Anda tidak jadi checkout pesanan!", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setPositiveButton("Ya, Checkout Pesanan!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                pd.setMessage("send data ... ");
                                pd.setCancelable(false);
                                pd.show();

                                Bundle data = getIntent().getExtras();
                                int id_meja = meja.getData().getId();

                                ApiService api = RetrofitMenu.getApiService();
                                Call<ResponseModelOrder> order = api.checkout(id_meja);
                                order.enqueue(new Callback<ResponseModelOrder>() {
                                    @Override
                                    public void onResponse(Call<ResponseModelOrder> call, Response<ResponseModelOrder> response) {
                                        pd.hide();
                                        Log.d("RETRO", "response : " + response.body().toString());
                                        String kode = response.body().getKode();
                                        String pesan = response.body().getPesan();
                                        if (kode.equals("1")) {
                                            Toast.makeText(Pesanan.this, pesan, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Pesanan.this, pesan, Toast.LENGTH_SHORT).show();
                                        }
                                        Intent i = new Intent(Pesanan.this, Riwayat.class);
                                        startActivity(i);
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseModelOrder> call, Throwable t) {
                                        pd.hide();
                                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                                    }
                                });
                            }
                        });
                builder.show();
            }
        });
    }
    private void ambilDataPesanan() {
        Meja meja = PrefUtil.getMeja(this, PrefUtil.USER_SESSION);

        ApiService api = RetrofitMenu.getApiService();
        Call<ListDataPesanan> call = api.getDataPesanan(
                meja.getData().getNo()
        );
        call.enqueue(new Callback<ListDataPesanan>() {
                         @Override
                         public void onResponse(Call<ListDataPesanan> call, Response<ListDataPesanan> response) {
                             if (response.isSuccessful()) {
                                 if (response.body().getSuccess().toString().equals("true")) {
                                     listData = response.body().getPesanan();
                                     for (int i = 0; i < listData.size(); i++) {
                                         Log.d(TAG, "onResponse: " + listData.get(i).getId());

                                     }
                                     recycler.setAdapter(new AdapterPesanan(Pesanan.this, listData));
                                 }

                             } else {

                             }
                         }

                         @Override
                         public void onFailure(Call<ListDataPesanan> call, Throwable t) {
                             Toast.makeText(Pesanan.this, "Response Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void pesanlagi(View view){
        Intent next = new Intent(Pesanan.this, Dashboard.class);
        startActivity(next);
    }

}
