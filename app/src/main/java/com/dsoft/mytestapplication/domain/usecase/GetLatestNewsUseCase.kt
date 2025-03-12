package com.dsoft.mytestapplication.domain.usecase

import com.dsoft.mytestapplication.domain.model.Article
import com.dsoft.mytestapplication.domain.repository.Repository
import javax.inject.Inject

class GetLatestNewsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Article> {
        return repository.getAllNews()
    }
}