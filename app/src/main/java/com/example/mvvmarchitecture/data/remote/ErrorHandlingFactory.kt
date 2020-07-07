package com.example.mvvmarchitecture.data.remote

import android.content.Context
import com.example.mvvmarchitecture.R
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Quang Nguyen on 6/4/20.
 */

fun Context.getErrorMessage(exception: BaseException?): String? {

    if (exception == null) return null

    when (exception.errorType) {
        ErrorType.NETWORK -> {
            return when (exception.cause) {
                is UnknownHostException -> this.resources.getString(R.string.no_internet_connection)
                is SocketTimeoutException -> this.resources.getString(R.string.connect_timeout)
                else -> this.resources.getString(R.string.unknown_error)
            }
        }
        ErrorType.HTTP -> {
            // get message base on http code to map with specification
            // the server and client can define several codes such as
            // 800: notify to app need force update new version
            // 900: notify to app that server maintain
            // ...
            return when (exception.httpCode) {
                // 401
                HttpURLConnection.HTTP_UNAUTHORIZED -> exception.cause?.message
                // 500
                HttpURLConnection.HTTP_INTERNAL_ERROR -> exception.cause?.message
                // 800: force update app
                HttpCodeClientServer.FORCE_UPDATE.code -> this.resources.getString(R.string.force_update_app)
                // 900: server maintain
                HttpCodeClientServer.SERVER_MAINTAIN.code -> this.resources.getString(R.string.server_maintain_message)

                else -> this.resources.getString(R.string.unknown_error)
            }
        }
        ErrorType.SERVER -> {

        }
        else -> return this.resources.getString(R.string.unknown_error)
    }
    return null
}

suspend fun handleException(throwable: Throwable): BaseException {
    return withContext(Dispatchers.Main) {
        when (throwable) {
            is UnknownHostException, is ConnectException, is SocketTimeoutException, is IOException ->
                BaseException.toNetworkError(throwable)

            is HttpException -> {
                val response = throwable.response()
                val httpCode = throwable.code()

                val serverErrorResponseBody = response?.errorBody()?.toString()
                val serverErrorResponse = try {
                    Gson().fromJson(serverErrorResponseBody, ServerErrorResponse::class.java)
                } catch (e: Exception) {
                    ServerErrorResponse()
                }

                if (serverErrorResponse != null) {
                    BaseException.toServerError(
                        serverErrorResponse = serverErrorResponse,
                        response = response,
                        httpCode = httpCode
                    )
                } else {
                    BaseException.toHttpError(
                        response = response,
                        httpCode = httpCode
                    )
                }
            }

            else -> BaseException.toUnexpectedError(throwable)
        }
    }
}

class BaseException(
    val errorType: ErrorType,
    val serverErrorResponse: ServerErrorResponse? = null,
    val response: Response<*>? = null,
    val httpCode: Int? = null,
    cause: Throwable? = null
) : RuntimeException(cause?.message, cause) {

    override val message: String?
        get() = when (errorType) {
            ErrorType.HTTP -> response?.message()
            ErrorType.NETWORK -> cause?.message
            ErrorType.SERVER -> serverErrorResponse?.error?.getOrNull(0)
            ErrorType.UNEXPECTED -> cause?.message
        }

    companion object {

        fun toHttpError(
            response: Response<*>?,
            httpCode: Int?
        ) = BaseException(
            errorType = ErrorType.HTTP,
            response = response,
            httpCode = httpCode
        )

        fun toNetworkError(
            cause: Throwable?
        ) = BaseException(
            errorType = ErrorType.NETWORK,
            cause = cause
        )

        fun toServerError(
            serverErrorResponse: ServerErrorResponse?,
            response: Response<*>?,
            httpCode: Int?
        ) = BaseException(
            errorType = ErrorType.SERVER,
            serverErrorResponse = serverErrorResponse,
            response = response,
            httpCode = httpCode
        )

        fun toUnexpectedError(
            cause: Throwable?
        ) = BaseException(
            errorType = ErrorType.UNEXPECTED,
            cause = cause
        )
    }
}

enum class ErrorType {
    /**
     * An [IO Exception] occurred while communicating to the server.
     */
    NETWORK,

    /**
     * A non-2xx HTTP status code was received from the server.
     */
    HTTP,

    /**
     * A error server with the code & message.
     */
    SERVER,

    /**
     * An internal error occurred while attempting to execute a request. It is best practice to
     * re-throw this exception so your application crashes.
     */
    UNEXPECTED
}

enum class HttpCodeClientServer(val code: Int) {
    FORCE_UPDATE(800),

    SERVER_MAINTAIN(900)
}

data class ServerErrorResponse(
    val message: String? = null,
    val error: List<String>? = null
)