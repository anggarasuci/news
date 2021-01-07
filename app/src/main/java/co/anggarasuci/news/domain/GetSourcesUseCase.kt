package co.anggarasuci.news.domain

import co.anggarasuci.news.data.model.RequestSources
import co.anggarasuci.news.data.model.ResponseSources
import co.anggarasuci.news.data.repository.NewsRepository
import co.anggarasuci.news.domain.Result
import co.anggarasuci.news.domain.UseCase
import co.anggarasuci.news.util.Constant

class GetSourcesUseCase(
    private val newsRepository: NewsRepository
): UseCase<ResponseSources, RequestSources>(){

    override suspend fun build(params: RequestSources?): Result<ResponseSources> {
        requireNotNull(params) { Constant.REQUIRED_PARAMS }
        return newsRepository.getSources(params)
    }

    fun createParams(category: String = ""): RequestSources {
        return RequestSources(category = category)
    }
}
