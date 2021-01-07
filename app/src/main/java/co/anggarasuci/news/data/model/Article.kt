package co.anggarasuci.news.data.model

import com.google.gson.annotations.SerializedName


data class RequestArticles(
    val keyword: String = "",
    val pages: Int = 0,
    val rows: Int = 0,
    val sourceId: String = ""
)

data class ResponseArticles(
    val articles: List<Article> = listOf()
)

data class Article(
    val source: Source = Source(),
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    @SerializedName("urlToImage")
    val urlToImage: String = "",
    @SerializedName("publishedAt")
    val publishedAt: String = "",
    val content: String = ""
)