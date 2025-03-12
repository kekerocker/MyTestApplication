package com.dsoft.mytestapplication.data.model

data class ArticleDTO(
    val source: SourceDTO?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?, // 2025-02-06T13:45:12Z
    val content: String?
)