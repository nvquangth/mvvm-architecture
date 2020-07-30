package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.ui.categorylist.CategoryListViewModel
import com.example.mvvmarchitecture.ui.main.MainViewModel
import com.example.mvvmarchitecture.ui.recipelist.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CategoryListViewModel(get()) }
    viewModel { RecipeListViewModel(get()) }
}