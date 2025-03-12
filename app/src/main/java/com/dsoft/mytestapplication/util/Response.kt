package com.dsoft.mytestapplication.util

sealed class Response<T>(
    val data: T? = null,
    val exception: Exception? = null,
    val message: String? = null
) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T) : Response<T>(data)
}