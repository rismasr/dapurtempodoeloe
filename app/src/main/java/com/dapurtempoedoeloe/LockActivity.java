package com.dapurtempoedoeloe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dapurtempoedoeloe.config.LockService;
import com.dapurtempoedoeloe.model.Meja;
import com.dapurtempoedoeloe.util.PrefUtil;

import java.util.concurrent.locks.Lock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LockActivity extends AppCompatActivity {

    private Button btnLock;

    private EditText edt_no_meja;

    private LockService lockService;

    public static void start(Context context) {
        Intent intent = new Intent(context, LockActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        edt_no_meja = findViewById(R.id.ediText_no_meja);
        btnLock = findViewById(R.id.btnLock);

        if(isSessionLogin()) {
            Dashboard.start(this);
            LockActivity.this.finish();
        }

        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lockAct();
            }
        });
    }
    void lockAct() {
        String no = edt_no_meja.getText().toString();

        if(TextUtils.isEmpty(no)) {
            edt_no_meja.setError("No Meja harus diisi!");
            return;
        }

        lockService = new LockService(this);
        lockService.doLock(no, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Meja meja = (Meja) response.body();

                if(meja != null) {
                    if(!meja.isError()) {
                        PrefUtil.putMeja(LockActivity.this, PrefUtil.USER_SESSION, meja);
                        Dashboard.start(LockActivity.this);
                        LockActivity.this.finish();
                    }
                    Toast.makeText(LockActivity.this, meja.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LockActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isSessionLogin() {
        return PrefUtil.getMeja(this, PrefUtil.USER_SESSION) != null;
    }
}