package com.example.mvvmarchitecture.data.model

/**
 * Created by Quang Nguyen on 7/5/20.
 */

/**
 * Status of a resource that is provided to the UI
 *
 * There are usually created by the Repository classes where they return LiveData<Resource<T>> to
 * pass back the latest data to the UI with its fetch status
 */
enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}