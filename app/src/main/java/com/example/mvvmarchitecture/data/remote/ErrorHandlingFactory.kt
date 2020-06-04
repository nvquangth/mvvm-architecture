package com.example.mvvmarchitecture.data.remote

import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.lang.RuntimeException

/**
 * Created by Quang Nguyen on 6/4/20.
 */

fun convertToBaseException(throwable: Throwable): BaseException =
    when(throwable) {
        is BaseException -> throwable

        is IOException -> BaseException.toNetworkError(throwable)

        is HttpException -> {
            val response = throwable.response()
            val httpCode = throwable.code()

            if (response?.errorBody() == null) {
                BaseException.toHttpError(
                    response = response,
                    httpCode = httpCode
                )
            }

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

data class ServerErrorResponse(
    val message: String? = null,
    val error: List<String>? = null
)