package com.example.mvvmarchitecture.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarchitecture.data.remote.convertToBaseException
import com.example.mvvmarchitecture.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Quang Nguyen on 6/3/20.
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * This variable is private because we do not want to expose MutableLiveData
     *
     * MutableLiveData allows anyone to set value, and ViewModel is the only class that should be
     * setting values.
     */
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _noInternetConnectionEvent = SingleLiveEvent<Unit>()
    val noInternetConnectionEvent: LiveData<Unit>
        get() = _noInternetConnectionEvent

    private val _connectTimeoutEvent = SingleLiveEvent<Unit>()
    val connectTimeoutEvent: LiveData<Unit>
        get() = _connectTimeoutEvent

    private val _forceUpdateAppEvent = SingleLiveEvent<Unit>()
    val forceUpdateAppEvent: LiveData<Unit>
        get() = _forceUpdateAppEvent

    private val _serverMaintainEvent = SingleLiveEvent<Unit>()
    val serverMaintainEvent: LiveData<Unit>
        get() = _serverMaintainEvent

    private val _unknownErrorEvent = SingleLiveEvent<Unit>()
    val unknownErrorEvent: LiveData<Unit>
        get() = _unknownErrorEvent

    private val coroutineExceptionHandlerScope = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            onError(throwable)
        }
    }

    open suspend fun onError(throwable: Throwable) {
        withContext(Dispatchers.Main) {
            when (throwable) {
                is UnknownHostException -> _noInternetConnectionEvent.call()

                is ConnectException -> _noInternetConnectionEvent.call()

                is SocketTimeoutException -> _connectTimeoutEvent.call()

                else -> {
                    val baseException = convertToBaseException(throwable)

                    when (baseException.httpCode) {
                        HttpURLConnection.HTTP_UNAUTHORIZED -> _errorMessage.value =
                            baseException.message
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> _errorMessage.value =
                            baseException.message
                        else -> _unknownErrorEvent.call()
                    }
                }
            }
        }

    }
}