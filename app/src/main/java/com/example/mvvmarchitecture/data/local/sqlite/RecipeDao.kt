package com.example.mvvmarchitecture.data.local.sqlite

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmarchitecture.data.model.Recipe

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipes: List<Recipe>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE name LIKE :name")
    fun searchRecipes(name: String): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun getRecipe(id: Int): LiveData<Recipe>

    @Query("SELECT * FROM recipe WHERE categoryId = :categoryId")
    fun getRecipesByCategory(categoryId: Int): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE isFavorite = 1")
    fun getFavorites(): LiveData<List<Recipe>>
}