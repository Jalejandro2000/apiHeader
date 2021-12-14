package com.example.apiheader;

import com.example.apiheader.Bancos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiBancos {

//    @Headers({"Public-Merchant-Id",
//            "79af2904a8ff47f2906a03e6fc46b6c7"})

    @GET("payouts/transfer/v1/bankList")
    Call<List<Bancos>> getBancos(        @Header("Public-Merchant-Id") String AuthToken,
                                         @Header("79af2904a8ff47f2906a03e6fc46b6c7") String UserId);

}
