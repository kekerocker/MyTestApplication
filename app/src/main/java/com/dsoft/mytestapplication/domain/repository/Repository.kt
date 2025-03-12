package com.dsoft.mytestapplication.domain.repository

import com.dsoft.mytestapplication.domain.model.Article

interface Repository {
    suspend fun getAllNews(): List<Article>
}