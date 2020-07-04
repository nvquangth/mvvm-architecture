package com.example.mvvmarchitecture.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvmarchitecture.data.local.sqlite.CategoryDao
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.local.pref.PrefHelper
import com.example.mvvmarchitecture.data.remote.ApiService

/**
 * Created by Quang Nguyen on 6/21/20.
 */
class CategoryRepository(
    private val api: ApiService,
    private val dao: CategoryDao,
    private val pref: PrefHelper
) {

    val categoryList: LiveData<List<Category>> = dao.getCategories()

    suspend fun fetchCategories() {
        if (!pref.isFetchedCategory()) {
            refreshCategory()
            pref.setFetchedCategory()
        }
    }

    suspend fun refreshCategory() {
        val data = api.getCategories().result
        dao.insert(data)
    }
}