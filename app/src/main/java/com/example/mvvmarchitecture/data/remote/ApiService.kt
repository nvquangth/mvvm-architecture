package com.example.mvvmarchitecture.data.remote

import com.example.mvvmarchitecture.data.model.Recipe
import com.example.mvvmarchitecture.data.model.response.CategoryListResponse
import com.example.mvvmarchitecture.data.model.response.RecipeListResponse
import com.example.mvvmarchitecture.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("category")
    suspend fun getCategories(): CategoryListResponse

    @GET("category/{category_id}")
    suspend fun getRecipesByCategory(
        @Path("category_id") categoryId: String,
        @Query("limit") limit: Int = Constants.LIMIT_DEFAULT,
        @Query("offset") offset: Int = Constants.OFFSET_DEFAULT
    ): RecipeListResponse

    @GET("recipe/{recipe_id}")
    suspend fun getRecipeDetail(@Path("recipe_id") recipeId: String): Recipe

    @GET("recipe")
    suspend fun getRecipesByName(
        @Query("name") name: String,
        @Query("limit") limit: Int = Constants.LIMIT_DEFAULT,
        @Query("offset") offset: Int = Constants.OFFSET_DEFAULT
    ): List<Recipe>
}