package co.anggarasuci.news.data.service

import co.anggarasuci.news.data.model.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface NewsService {
    companion object {
        const val ENDPOINT_HEADLINE = "top-headlines"
        const val ENDPOINT_EVERYTHING = "everything"
        const val ENDPOINT_SOURCES = "sources"
    }
}