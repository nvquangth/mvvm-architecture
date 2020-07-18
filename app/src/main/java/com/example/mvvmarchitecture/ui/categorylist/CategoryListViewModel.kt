package com.example.mvvmarchitecture.ui.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mvvmarchitecture.base.BaseViewModel
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.model.Resource
import com.example.mvvmarchitecture.data.repository.CategoryRepository

/**
 * Created by Quang Nguyen on 6/20/20.
 */
class CategoryListViewModel(
    private val repository: CategoryRepository
) : BaseViewModel() {

    private val _retry = MutableLiveData(false)
    val retry: LiveData<Boolean>
        get() = _retry

    val categories: LiveData<Resource<List<Category>>> = Transformations.switchMap(_retry) {
        repository.categories()
    }

    fun reLoadCategories() {
        _retry.value = true
    }
}