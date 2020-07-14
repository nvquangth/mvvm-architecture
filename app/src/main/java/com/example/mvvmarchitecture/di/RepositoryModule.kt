package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.data.repository.CategoryRepository
import org.koin.dsl.module

/**
 * Created by Quang Nguyen on 6/3/20.
 */

val repositoryModule = module {
    single { CategoryRepository(get(), get()) }
}