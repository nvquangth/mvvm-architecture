package com.example.mvvmarchitecture.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mvvmarchitecture.data.local.pref.PrefHelper
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
    private val dao: CategoryDao,
    private val pref: PrefHelper
) {

//    val categories: LiveData<List<Category>> = dao.getCategories()
//        .switchMap {
//            liveData {
//                try {
//                    if (it.isEmpty()) {
//                        Log.d("Repository", "list is empty")
//                        val data = api.getCategories().result
//                        dao.insert(data)
//                    } else {
//                        emit(it)
//                    }
//                } catch (e: Exception) {
//                    Log.d("Repository", "Exception: ${e.message}")
//                }
//            }
//        }

    val categories: LiveData<Resource<List<Category>>> = liveData {
        emit(Resource.loading(null))

        try {
            val data = api.getCategories().result
            dao.insert(data)

            emit(Resource.success(data))
        } catch (e: Exception) {

            val data = dao.getCategories().value

            if (!data.isNullOrEmpty()) {
                emit(Resource.success(data))
            } else {
                val ex = handleException(e)
                emit(Resource.error(ex, null))
            }
        }
    }
}