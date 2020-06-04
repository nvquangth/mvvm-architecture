package com.example.mvvmarchitecture.data.local.sqlite

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.model.Recipe

@Database(entities = [Category::class, Recipe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun recipeDao(): RecipeDao
}