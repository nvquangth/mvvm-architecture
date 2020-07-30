package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.data.repository.CategoryRepository
import com.example.mvvmarchitecture.data.repository.RecipeRepository
import org.koin.dsl.module

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val repositoryModule = module {
    single { CategoryRepository(get(), get()) }
    single { RecipeRepository(get(), get()) }
}