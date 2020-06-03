package com.example.mvvmarchitecture.data.remote

import com.example.mvvmarchitecture.data.model.Category
import retrofit2.http.GET

interface ApiService {
    @GET("category")
    fun getCategories(): List<Category>
}