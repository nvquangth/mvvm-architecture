package com.example.mvvmarchitecture.data.local.pref

interface PrefHelper {

    suspend fun setFetchedCategory()

    suspend fun isFetchedCategory(): Boolean

    suspend fun remove(key: String)

    suspend fun clear()
}