package com.dsoft.mytestapplication.ui.news.state

import com.dsoft.mytestapplication.domain.model.Article

data class NewsState(
    val list: List<Article> = emptyList()
)