package com.example.mvvmarchitecture.ui.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mvvmarchitecture.base.BaseViewModel
import com.example.mvvmarchitecture.data.model.Category
import com.example.mvvmarchitecture.data.model.Recipe
import com.example.mvvmarchitecture.data.model.Resource
import com.example.mvvmarchitecture.data.repository.RecipeRepository

/**
 * Created by Quang Nguyen on 7/18/20.
 */
class RecipeListViewModel(
    private val repository: RecipeRepository
) : BaseViewModel() {

    private val _category = MutableLiveData<Category>()
    val category: LiveData<Category>
        get() = _category

    private val _recipes = MutableLiveData<Resource<List<Recipe>>>()
    val recipes: LiveData<Resource<List<Recipe>>> = Transformations.switchMap(_category) {
        repository.getRecipes(it.id)
    }

    fun setCategory(category: Category) {
        if (_category.value == null) {
            _category.value = category
        }
    }
}