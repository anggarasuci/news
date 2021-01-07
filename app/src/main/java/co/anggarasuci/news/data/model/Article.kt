package co.anggarasuci.news.data.model


data class RequestArticles(
    val keyword: String = "",
    val pages: Int = 0,
    val rows: Int = 0,
    val sources: String = ""
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
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
)