package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val viewModelModule = module {
    viewModel { MainViewModel() }
}