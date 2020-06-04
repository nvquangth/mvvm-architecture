package com.example.mvvmarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmarchitecture.data.local.sqlite.AppDatabase
import com.example.mvvmarchitecture.util.Constants
import org.koin.dsl.module

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideCategoryDao(get()) }
    single { provideRecipeDao(get()) }
}

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()

fun provideCategoryDao(database: AppDatabase) = database.categoryDao()

fun provideRecipeDao(database: AppDatabase) = database.recipeDao()