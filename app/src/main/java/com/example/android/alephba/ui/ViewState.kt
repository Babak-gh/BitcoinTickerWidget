package com.example.android.alephba.ui

import com.example.android.alephba.data.model.ErrorModel


sealed class ViewState<out T>
data class Data<out T>(val data: T) : ViewState<T>()
data class UiError(val error: ErrorModel) : ViewState<Nothing>()
object Loading : ViewState<Nothing>()
