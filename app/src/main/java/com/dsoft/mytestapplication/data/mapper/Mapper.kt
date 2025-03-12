package com.dsoft.mytestapplication.data.mapper

import com.dsoft.mytestapplication.data.model.ArticleDTO
import com.dsoft.mytestapplication.data.model.SourceDTO
import com.dsoft.mytestapplication.domain.model.Article
import com.dsoft.mytestapplication.domain.model.Source
import java.time.LocalDateTime

fun ArticleDTO.toDomain(): Article {
    return Article(
        source = source.let { it.toDomain() },
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty(),
        publishedAt = LocalDateTime.now(),
        content = content.orEmpty()
    )
}

private fun SourceDTO?.toDomain(): Source {
    return Source(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty()
    )
}