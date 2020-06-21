package com.example.mvvmarchitecture.ui.categorylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmarchitecture.base.BaseViewModel
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.repository.CategoryRepository
import kotlinx.coroutines.launch

/**
 * Created by Quang Nguyen on 6/20/20.
 */
class CategoryListViewModel(
    private val repository: CategoryRepository
): BaseViewModel() {

    val categoryList = repository.categoryList

    fun fetchCategoryList() {
        viewModelScope.launch {
            repository.fetchCategory()
        }
    }
}