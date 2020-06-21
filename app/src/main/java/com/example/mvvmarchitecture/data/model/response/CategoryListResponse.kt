package com.example.mvvmarchitecture.data.model.response

import com.example.mvvmarchitecture.data.model.Category

/**
 * Created by Quang Nguyen on 6/21/20.
 */
class CategoryListResponse(status: Int, message: String, val result: List<Category>) :
    BaseResponse(status, message)