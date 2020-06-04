package com.example.mvvmarchitecture.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Created by Quang Nguyen on 6/3/20.
 */
abstract class BaseViewModel: ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        onError(throwable)
    }

    private fun onError(throwable: Throwable) {

    }
}