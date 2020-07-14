package com.example.mvvmarchitecture.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.mvvmarchitecture.data.local.sqlite.CategoryDao
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.model.Resource
import com.example.mvvmarchitecture.data.remote.ApiService
import com.example.mvvmarchitecture.data.remote.handleException

/**
 * Created by Quang Nguyen on 6/21/20.
 */
class CategoryRepository(
    private val api: ApiService,
    private val dao: CategoryDao
) {

    fun categories(): LiveData<Resource<List<Category>>> = liveData {
        emit(Resource.loading(null))

        try {
            val data = api.getCategories().result
            dao.insert(data)

            emit(Resource.success(data))
        } catch (e: Exception) {

            val ex = handleException(e)

            emitSource(dao.getCategories().map {
                if (!it.isNullOrEmpty()) {
                    Resource.success(it)
                } else {
                    Resource.error(ex, null)
                }
            })
        }
    }
}