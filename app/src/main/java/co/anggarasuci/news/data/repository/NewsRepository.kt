package co.anggarasuci.news.data.repository

import co.anggarasuci.news.data.model.RequestArticles
import co.anggarasuci.news.data.model.RequestSources
import co.anggarasuci.news.data.model.ResponseArticles
import co.anggarasuci.news.data.model.ResponseSources
import co.anggarasuci.news.data.remote.NewsRemoteDataSource
import co.anggarasuci.news.domain.Result

class NewsRepository(
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    suspend fun getSources(params: RequestSources): Result<ResponseSources> {
        return when (val result = newsRemoteDataSource.getSources(params)) {
            is Result.Success -> Result.Success(result.value.content)
            is Result.Error -> result
        }
    }

    suspend fun getArticles(params: RequestArticles): Result<ResponseArticles> {
        return when (val result = newsRemoteDataSource.getArticles(params)) {
            is Result.Success -> Result.Success(result.value.content)
            is Result.Error -> result
        }
    }
}