package co.anggarasuci.news.data.service

import co.anggarasuci.news.data.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface NewsService {
    companion object {
        const val ENDPOINT_HEADLINE = "top-headlines"
        const val ENDPOINT_SOURCES = "sources"
    }

    @GET
    fun getSourcesAsync(@Url url: String = "", @QueryMap params: Map<String, String>): Deferred<Response<List<Source>>>

    @GET
    fun getArticlesAsync(@Url url: String = "", @QueryMap params: Map<String, String>): Deferred<Response<List<Article>>>
}