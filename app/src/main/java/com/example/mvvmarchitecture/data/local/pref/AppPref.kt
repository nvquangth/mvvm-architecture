package com.example.mvvmarchitecture.data.local.pref

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

class AppPref(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : PrefHelper {

    companion object {
        private const val PREF_FETCH_CATEGORY_FROM_NETWORK = "PREF_FETCH_CATEGORY_FROM_NETWORK"
    }

    override suspend fun setFetchedCategory() {
        sharedPreferences.edit {
            putBoolean(PREF_FETCH_CATEGORY_FROM_NETWORK, true)
        }
    }

    override suspend fun isFetchedCategory(): Boolean {
        return sharedPreferences.getBoolean(PREF_FETCH_CATEGORY_FROM_NETWORK, false)
    }

    override suspend fun remove(key: String) {
        sharedPreferences.edit {
            remove(key)
        }
    }

    override suspend fun clear() {
        sharedPreferences.edit {
            clear()
        }
    }
}