package com.example.mvvmarchitecture.data.model.response

import com.example.mvvmarchitecture.data.model.Recipe

/**
 * Created by Quang Nguyen on 7/18/20.
 */
class RecipeListResponse(status: Int, message: String, val result: List<Recipe>) :
    BaseResponse(status, message) {
}