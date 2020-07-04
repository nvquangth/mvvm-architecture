package com.example.mvvmarchitecture.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mvvmarchitecture.data.local.pref.AppPref
import com.example.mvvmarchitecture.data.local.pref.PrefHelper
import com.example.mvvmarchitecture.data.local.sqlite.AppDatabase
import com.example.mvvmarchitecture.utils.Constants
import com.google.gson.Gson
import org.koin.dsl.module

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideCategoryDao(get()) }
    single { provideRecipeDao(get()) }
    single { provideGson() }
    single { provideSharedPreference(get()) }
    single<PrefHelper> { AppPref(get(), get()) }
}

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()

fun provideCategoryDao(database: AppDatabase) = database.categoryDao()

fun provideRecipeDao(database: AppDatabase) = database.recipeDao()

fun provideGson() = Gson()

fun provideSharedPreference(context: Context) =
    context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)