package com.dsoft.mytestapplication

import androidx.core.net.toUri
import com.dsoft.mytestapplication.domain.model.Article
import com.dsoft.mytestapplication.domain.model.Source
import com.dsoft.mytestapplication.domain.repository.Repository
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Как я уже говорил на техническом интервью, тесты не умею писать,
 * попробовал вот такой простой сейчас сделать для теста.
 */
class RepositoryTest {

    private val repository: Repository = mock()

    @Test
    fun `getAllNews should return list of articles`() = runTest {
        // Arrange
        val expectedArticles = listOf(
            Article(
                source = Source(
                    id = "some id",
                    name = "BBC"
                ),
                author = "J. Rowling",
                title = "Harry Potter",
                url = "some url",
                urlToImage = "some image url",
                publishedAt = LocalDateTime.of(
                    LocalDate.of(2024, 1, 1),
                    LocalTime.of(4, 24)
                ),
                content = "some content",
                description = "some description"
            )
        )
        whenever(repository.getAllNews()).thenReturn(expectedArticles)

        // Act
        val result = repository.getAllNews()

        // Assert
        assert(result == expectedArticles)
    }

}