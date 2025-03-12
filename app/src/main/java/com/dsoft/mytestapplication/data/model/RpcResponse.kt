package com.dsoft.mytestapplication.data.model

interface RpcResponse {
    val status: String
    val totalResults: Int
    val message: String?
}