package com.example.mvvmarchitecture.data.remote

import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.model.Recipe
import com.example.mvvmarchitecture.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("category")
    suspend fun getCategories(): List<Category>

    @GET("category/{category_id}?limit={limit}&&offset={offset}")
    suspend fun getRecipesByCategory(
        @Path("category_id") categoryId: String,
        @Query("limit") limit: Int = Constants.LIMIT_DEFAULT,
        @Query("offset") offset: Int = Constants.OFFSET_DEFAULT
    ): List<Recipe>

    @GET("recipe/{recipe_id}")
    suspend fun getRecipeDetail(@Path("recipe_id") recipeId: String): Recipe

    @GET("recipe?q={name}&?limit={limit}&&offset={offset}")
    suspend fun getRecipesByName(
        @Path("name") name: String,
        @Query("limit") limit: Int = Constants.LIMIT_DEFAULT,
        @Query("offset") offset: Int = Constants.OFFSET_DEFAULT
    ): List<Recipe>
}