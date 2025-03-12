package com.dsoft.mytestapplication.data.repository

import com.dsoft.mytestapplication.data.api.NetworkApi
import com.dsoft.mytestapplication.data.mapper.toDomain
import com.dsoft.mytestapplication.domain.model.Article
import com.dsoft.mytestapplication.domain.repository.Repository
import com.dsoft.mytestapplication.util.mapNetError
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: NetworkApi
) : Repository {
    override suspend fun getAllNews(): List<Article> {
        return api.getNews()
            .mapNetError()
            .articles
            .map { it.toDomain() }
    }
}