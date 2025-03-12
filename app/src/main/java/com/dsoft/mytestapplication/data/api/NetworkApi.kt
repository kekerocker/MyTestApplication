package com.dsoft.mytestapplication.data.api

import com.dsoft.mytestapplication.data.model.NewsDTO
import com.dsoft.mytestapplication.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    /**
     * какой-нибудь query надо было задать, чтобы он совершил поиск, иначе выбросит 400 ошибку.
     * При желании, можете пустую строку здесь оставить, чтобы проверить,
     * как ошибка обработается у меня
     */
    @GET("everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("q") query: String = "Tokyo"
    ): Response<NewsDTO>
}