package com.example.android.alephba.ui


sealed class ViewState<out T>
data class Data<out T>(val data: T) : ViewState<T>()
data class Error(val error: String) : ViewState<Nothing>()
object Loading : ViewState<Nothing>()
