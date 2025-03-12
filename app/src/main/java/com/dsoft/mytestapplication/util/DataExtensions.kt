package com.dsoft.mytestapplication.util

import com.dsoft.mytestapplication.data.model.RpcResponse
import com.google.gson.Gson
import retrofit2.Response

inline fun <reified T : RpcResponse> Response<T>.mapNetError(): T {
    if (isSuccessful) return body() ?: throw RuntimeException("Response body is null")
     else {
        val errorBody = errorBody()?.string()
        val errorResponse = Gson().fromJson(errorBody, T::class.java)
        throw RuntimeException(errorResponse.message.toString())
    }
}