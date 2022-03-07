package com.dapurtempoedoeloe;

import android.os.Bundle;

import com.dapurtempoedoeloe.adapter.AdapterMenu;
import com.dapurtempoedoeloe.config.ApiService;
import com.dapurtempoedoeloe.config.RetrofitMenu;
import com.dapurtempoedoeloe.model.MenuItem;
import com.dapurtempoedoeloe.model.ResponseMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Minuman extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minuman);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Minuman");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvListMinuman);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tampilMinuman();
    }
    private void tampilMinuman() {
        ApiService api = RetrofitMenu.getApiService();
        Call<ResponseMenu> animalsCall = api.request_show_all_minuman();
        animalsCall.enqueue(new Callback<ResponseMenu>() {
            @Override
            public void onResponse(Call<ResponseMenu> call, Response<ResponseMenu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<MenuItem> data_menu = response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        AdapterMenu adapter = new AdapterMenu(Minuman.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Minuman.this, "Tidak ada minuman untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseMenu> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}