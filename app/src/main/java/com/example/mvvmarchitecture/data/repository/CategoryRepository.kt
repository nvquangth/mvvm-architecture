package com.example.mvvmarchitecture.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvmarchitecture.data.local.sqlite.CategoryDao
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.remote.ApiService

/**
 * Created by Quang Nguyen on 6/21/20.
 */
class CategoryRepository(
    private val api: ApiService,
    private val dao: CategoryDao
) {

    val categoryList: LiveData<List<Category>> = dao.getCategories()

    suspend fun refreshCategoryList() {
        val data = api.getCategories()
        dao.insert(data.result)
    }
}