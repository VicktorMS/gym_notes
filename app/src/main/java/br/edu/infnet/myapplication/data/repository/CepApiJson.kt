package br.edu.infnet.myapplication.data.repository

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


// Construído conforme codelab:
// Android Kotlin Fundamentals:8.1 Getting data from the internet
// https://developer.android.com/codelabs/kotlin-android-training-internet-data#0

private const val BASE_URL =
    "https://viacep.com.br/"

private val retrofitJson = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CepApiService {
    @GET("ws/{cep}/json")
    fun getCepJson(@Path("cep") cep: String):
            Call<String>
}

object CepApiJson {
    val retrofitService : CepApiService by lazy {
        retrofitJson.create(CepApiService::class.java) }
}