package com.example.mvvmarchitecture.ui.categorylist

import androidx.lifecycle.viewModelScope
import com.example.mvvmarchitecture.base.BaseViewModel
import com.example.mvvmarchitecture.data.repository.CategoryRepository
import kotlinx.coroutines.launch

/**
 * Created by Quang Nguyen on 6/20/20.
 */
class CategoryListViewModel(
    private val repository: CategoryRepository
): BaseViewModel() {

    init {
        fetchCategories()
    }

    val categoryList = repository.categoryList

    fun fetchCategories() {
        viewModelScope.launch(coroutineExceptionHandlerScope) {
            repository.fetchCategories()
        }
    }

    fun refreshCategories() {
        viewModelScope.launch(coroutineExceptionHandlerScope) {
            repository.refreshCategory()
        }
    }
}