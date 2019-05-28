package com.br.k2testesantander.network.api

import com.br.k2testesantander.network.model.LoginResponse
import com.br.k2testesantander.network.model.StatementResponse
import retrofit2.Call
import retrofit2.http.*


interface Retrofit {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("user") user: String, @Field("password") password : String ) : Call<LoginResponse>

    @GET("statements/{idUser}")
    fun getStatements(@Path("idUser") idUser : Int) : Call<StatementResponse>
}