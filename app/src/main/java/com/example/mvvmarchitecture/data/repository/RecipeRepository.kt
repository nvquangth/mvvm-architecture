package com.example.mvvmarchitecture.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.mvvmarchitecture.data.local.sqlite.RecipeDao
import com.example.mvvmarchitecture.data.model.Recipe
import com.example.mvvmarchitecture.data.model.Resource
import com.example.mvvmarchitecture.data.remote.ApiService
import com.example.mvvmarchitecture.data.remote.handleException

/**
 * Created by Quang Nguyen on 7/18/20.
 */
class RecipeRepository(
    private val api: ApiService,
    private val dao: RecipeDao
) {

    fun getRecipes(
        categoryId: String,
        limit: Int = 0,
        offset: Int = 0
    ): LiveData<Resource<List<Recipe>>> =
        liveData {
            emit(Resource.loading(null))

            try {
                val data = api.getRecipesByCategory(categoryId).result.map {
                    it.categoryId = categoryId
                    it
                }

                dao.insert(data)

                emit(Resource.success(data))
            } catch (e: Exception) {
                val ex = handleException(e)

                emitSource(dao.getRecipesByCategory(categoryId).map {
                    if (!it.isNullOrEmpty()) {
                        Resource.success(it)
                    } else {
                        Resource.error(ex, null)
                    }
                })
            }
        }
}