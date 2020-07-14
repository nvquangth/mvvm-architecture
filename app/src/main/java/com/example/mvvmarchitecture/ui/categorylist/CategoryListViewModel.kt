package com.example.mvvmarchitecture.ui.categorylist

import com.example.mvvmarchitecture.base.BaseViewModel
import com.example.mvvmarchitecture.data.repository.CategoryRepository

/**
 * Created by Quang Nguyen on 6/20/20.
 */
class CategoryListViewModel(
    private val repository: CategoryRepository
): BaseViewModel() {

    val categories = repository.categories()
}