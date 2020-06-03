package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.BuildConfig
import com.example.mvvmarchitecture.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Quang Nguyen on 6/3/20.
 */

fun provideRetrofit() = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)