package com.example.android.alephba.data.model

sealed class Response<out T> {
    fun isSuccess() = this is Success
}

data class Success<out T>(val data: T) : Response<T>()
data class Error(val error: String) : Response<Nothing>()
object NetworkError : Response<Nothing>()

