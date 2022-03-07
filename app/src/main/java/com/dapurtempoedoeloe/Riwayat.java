package com.dapurtempoedoeloe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dapurtempoedoeloe.adapter.AdapterPesanan;
import com.dapurtempoedoeloe.config.ApiService;
import com.dapurtempoedoeloe.config.RetrofitMenu;
import com.dapurtempoedoeloe.model.ListDataPesanan;
import com.dapurtempoedoeloe.model.Meja;
import com.dapurtempoedoeloe.model.PesananModel;
import com.dapurtempoedoeloe.util.PrefUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Riwayat extends AppCompatActivity {

    ArrayList<PesananModel> listData;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        setTitle("Riwayat Pemesanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listData = new ArrayList<>();

        ambilDataPesanan();

        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new AdapterPesanan(this, listData));
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
                                     recycler.setAdapter(new AdapterPesanan(Riwayat.this, listData));
                                 }

                             } else {

                             }
                         }

                         @Override
                         public void onFailure(Call<ListDataPesanan> call, Throwable t) {
                             Toast.makeText(Riwayat.this, "Response Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
