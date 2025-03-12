package com.dsoft.mytestapplication.domain.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: LocalDateTime, // 2025-02-06T13:45:12Z
    val content: String
) {
    val formattedDate: String by lazy {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        formatter.format(publishedAt)
    }
}