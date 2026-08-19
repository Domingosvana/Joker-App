package com.kody.jokeapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object HTTPClient {

    private const val BASE_URL = "https://atway.tiagoaguiar.dev/fenix/jokerapp/jokes/"

    val   API_KEY = "9dc55393-9aa6-4fb7-b9f3-9c09a949a77f"
    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }



    fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .build()


}