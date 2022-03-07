package com.dapurtempoedoeloe.config;

import com.dapurtempoedoeloe.model.ListDataPesanan;
import com.dapurtempoedoeloe.model.ResponseModelOrder;
import com.dapurtempoedoeloe.model.ResponseMenu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET(Rest.API_MAKANAN)
    Call<ResponseMenu> request_show_all_makanan();

    @GET(Rest.API_MINUMAN)
    Call<ResponseMenu> request_show_all_minuman();

    @FormUrlEncoded
    @POST(Rest.API_ORDER)
    Call<ResponseModelOrder> sendOrder(@Field("id_menu") int id_menu,
                                       @Field("id_meja") int id_meja,
                                       @Field("jumlah") String jumlah);

    @FormUrlEncoded
    @POST(Rest.API_CHECKOUT)
    Call<ResponseModelOrder> checkout(@Field("id_meja") int id_meja);

    @GET(Rest.PESANAN)
    Call<ListDataPesanan>getDataPesanan(
            @Query("no_meja") int no
    );
}
