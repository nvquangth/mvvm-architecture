package com.example.mvvmarchitecture

import android.app.Application
import com.example.mvvmarchitecture.di.databaseModule
import com.example.mvvmarchitecture.di.networkModule
import com.example.mvvmarchitecture.di.repositoryModule
import com.example.mvvmarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Quang Nguyen on 6/3/20.
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}