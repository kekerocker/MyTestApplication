package com.dsoft.mytestapplication.data.model

data class NewsDTO(
    override val status: String,
    override val totalResults: Int,
    override val message: String?,
    val articles: List<ArticleDTO>
) : RpcResponse


