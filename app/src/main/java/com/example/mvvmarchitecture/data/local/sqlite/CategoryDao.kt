package com.example.mvvmarchitecture.data.local.sqlite

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmarchitecture.data.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<Category>)

    @Delete
    suspend fun delete(category: Category)

    @Update
    suspend fun update(category: Category)

    @Query("SELECT * FROM category ORDER BY totalRecipe DESC")
    fun getCategories(): LiveData<List<Category>>
}