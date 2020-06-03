package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.BuildConfig
import com.example.mvvmarchitecture.data.remote.ApiService
import com.example.mvvmarchitecture.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    okHttpBuilder.apply {
        addInterceptor(logging)
        connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
        readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    return okHttpBuilder.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
