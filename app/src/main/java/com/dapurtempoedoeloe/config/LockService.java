package com.dapurtempoedoeloe.config;

import android.content.Context;

import retrofit2.Callback;

public class LockService {

    private LockInterface lockInterface;

    public LockService(Context context) {
        lockInterface = RetrofitBuilder.builder(context)
                .create(LockInterface.class);
    }
    public void doLock(String no, Callback callback) {
        lockInterface.lock(no).enqueue(callback);
    }
}
