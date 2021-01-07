package co.anggarasuci.news.data.remote

import co.anggarasuci.news.BuildConfig
import co.anggarasuci.news.data.model.*
import co.anggarasuci.news.data.service.NewsService
import co.anggarasuci.news.domain.Result
import co.anggarasuci.news.network.ResponseParser
import co.anggarasuci.news.util.Param

class NewsRemoteDataSource(
    private val newsService: NewsService
) {
    private var baseUrl = BuildConfig.APP_SERVICE_BASE_URL

    suspend fun getSources(param: RequestSources): Result<Response<List<Source>>> {
        val paramsMap = hashMapOf(Param.CategoryParam to param.category,
        "language" to "en")

        val url = baseUrl.plus(NewsService.ENDPOINT_SOURCES)
        return ResponseParser<List<Source>>().parseResult(
            newsService.getSourcesAsync(url, paramsMap))
    }

    suspend fun getArticles(param: RequestArticles): Result<Response<List<Article>>> {
        val paramsMap = hashMapOf(
            Param.SearchParam to param.keyword,
            Param.PageParam to param.pages.toString(),
            Param.RowParam to param.rows.toString(),
            Param.SourceParam to param.sourceId
        )
        val url = baseUrl.plus(NewsService.ENDPOINT_HEADLINE)
        return ResponseParser<List<Article>>().parseResult(
            newsService.getArticlesAsync(url, paramsMap))
    }
}