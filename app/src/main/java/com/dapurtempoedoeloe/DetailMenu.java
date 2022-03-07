package com.dapurtempoedoeloe;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.dapurtempoedoeloe.config.ApiService;
import com.dapurtempoedoeloe.config.RetrofitMenu;
import com.dapurtempoedoeloe.model.Meja;
import com.dapurtempoedoeloe.model.ResponseModelOrder;
import com.dapurtempoedoeloe.util.PrefUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMenu extends AppCompatActivity {

    ImageView ivGambarMenu;
    TextView tv_detail_harga, tv_detail_stock;
    EditText jmlh;
    WebView wvDeskripsi;
    int quantity;
    Button pesan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivGambarMenu = (ImageView) findViewById(R.id.ivGambarMenu);
        tv_detail_harga = (TextView) findViewById(R.id.tv_detail_harga);
        tv_detail_stock = (TextView) findViewById(R.id.tv_detail_stock);
        wvDeskripsi = (WebView) findViewById(R.id.wvDeskripsi);
        jmlh = (EditText) findViewById(R.id.quantity_textview);
        pesan = (Button)findViewById(R.id.btnPesan);
        showDetailMenu();

        pd = new ProgressDialog(this);
        Meja meja = PrefUtil.getMeja(this, PrefUtil.USER_SESSION);

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        DetailMenu.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("ADD TO CART");
                builder.setMessage("Apakah anda yakin ingin menambah daftar pesanan?");
                builder.setNegativeButton("Tidak, gagalkan!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DetailMenu.this,
                                        "Anda tidak jadi menambah pesanan!", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setPositiveButton("Ya, Tambah Pesanan!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                pd.setMessage("send data ... ");
                                pd.setCancelable(false);
                                pd.show();

                                Bundle data = getIntent().getExtras();
                                int id_menu = data.getInt("ID");
                                int id_meja = meja.getData().getId();
                                String jumlah = jmlh.getText().toString().trim();

                                ApiService api = RetrofitMenu.getApiService();
                                Call<ResponseModelOrder> order = api.sendOrder(id_menu, id_meja, jumlah);
                                order.enqueue(new Callback<ResponseModelOrder>() {
                                    @Override
                                    public void onResponse(Call<ResponseModelOrder> call, Response<ResponseModelOrder> response) {
                                        pd.hide();
                                        Log.d("RETRO", "response : " + response.body().toString());
                                        String kode = response.body().getKode();
                                        String pesan = response.body().getPesan();
                                        if (kode.equals("1")) {
                                            Toast.makeText(DetailMenu.this, pesan, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(DetailMenu.this, pesan, Toast.LENGTH_SHORT).show();
                                        }
                                        Intent i = new Intent(DetailMenu.this, Pesanan.class);
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

    private void showDetailMenu() {
        Bundle data = getIntent().getExtras();
        int id = data.getInt("ID");
        int id_kategori = data.getInt("ID_KATEGORI");
        String nama = data.getString("NAMA");
        System.out.println("nama"+ nama);
        int harga = data.getInt("HARGA");
        String deskripsi = data.getString("DESKRIPSI");
        int stok = data.getInt("STOK");
        System.out.println("stok"+ stok);
        String gambar = data.getString("GAMBAR");
        getSupportActionBar().setTitle(nama);
        tv_detail_harga.setText("Rp." + harga);
        tv_detail_stock.setText(stok + "");

        Glide.with(this)
                .load(gambar)
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .into(ivGambarMenu);
        wvDeskripsi.getSettings().setJavaScriptEnabled(true);
        wvDeskripsi.loadData(deskripsi, "text/html; charset=utf-8", "UTF-8");
    }

    public void increment(View view){

        showDetailMenu();


        Bundle data = getIntent().getExtras();
        int stok = data.getInt("STOK");

//        String stock2 = data.getString(Konstanta.DATA_STOCK);
//
//
//        int stock = getIntent().getIntExtra(Konstanta.DATA_STOCK, Integer.parseInt(stock2));
        if(quantity>=stok){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);
            alertDialogBuilder.setTitle("Maaf Jumlah Pesanan anda telah melebihi stock!");

            alertDialogBuilder
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("Tutup",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }

    private void display(int number) {
        EditText quantityTextView = (EditText) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }
}
