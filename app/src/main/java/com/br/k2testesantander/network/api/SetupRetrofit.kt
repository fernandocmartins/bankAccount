package com.br.k2testesantander.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SetupRetrofit {

    companion object {
        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}