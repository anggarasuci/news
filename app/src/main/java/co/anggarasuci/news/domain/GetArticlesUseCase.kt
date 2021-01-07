package co.anggarasuci.news.domain

import co.anggarasuci.news.data.model.RequestArticles
import co.anggarasuci.news.data.model.ResponseArticles
import co.anggarasuci.news.data.repository.NewsRepository
import co.anggarasuci.news.domain.Result
import co.anggarasuci.news.domain.UseCase
import co.anggarasuci.news.util.Constant

class GetArticlesUseCase(
    private val newsRepository: NewsRepository
): UseCase<ResponseArticles, RequestArticles>(){

    override suspend fun build(params: RequestArticles?): Result<ResponseArticles> {
        requireNotNull(params) { Constant.REQUIRED_PARAMS }
        return newsRepository.getArticles(params)
    }

    fun createParams(
        page: Int = 1,
        rows: Int = Constant.PageSize,
        keyword: String = "",
        sources: String = ""): RequestArticles {
        return RequestArticles(
            keyword = keyword,
            rows = rows,
            pages = page,
            sourceId = sources
        )
    }
}
