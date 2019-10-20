package com.example.retrofit

import com.example.rxjava.BuildConfig
import com.squareup.okhttp.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object
ServiceGenerator {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"




    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient().build())
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    private val requestApi = retrofit.create(RequestApi::class.java)

    fun getRequestApi(): RequestApi {
        return requestApi
    }


    /**
     * @return
     */
    private fun httpClient(): okhttp3.OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        //logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);


        if (BuildConfig.DEBUG)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = okhttp3.OkHttpClient.Builder()


        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging)
//        httpClient.readTimeout(SOCKET_TIMEOUT.toLong(), TimeUnit.SECONDS)
//        httpClient.writeTimeout(SOCKET_TIMEOUT.toLong(), TimeUnit.SECONDS)
        return httpClient
    }


}