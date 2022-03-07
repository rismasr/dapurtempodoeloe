package com.dapurtempoedoeloe.config;

import com.dapurtempoedoeloe.model.Meja;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Field;

public interface LockInterface {
    @FormUrlEncoded
    @POST(Rest.API_LOGIN)
    Call<Meja> lock(
            @Field("no") String no);
}
