package com.example.weatherapp.data.networking

import com.example.weatherapp.data.response.ResponseByCity
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun searchByCity(
        @Query("q") q: String,
        @Query("appid") appId: String
    ) : Observable<ResponseByCity>


    @GET("data/2.5/find")
    fun search(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units : String = "metric",
        @Query("appid") appId: String
    ) : Observable<ResponseByCity>



    @GET("data/2.5/weather")
    fun searchByLatLon(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ) : Observable<ResponseByCity>
}
