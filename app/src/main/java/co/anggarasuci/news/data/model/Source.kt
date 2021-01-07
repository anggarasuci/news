package co.anggarasuci.news.data.model


data class RequestSources(
    val keyword: String = "",
    val pages: Int = 0,
    val rows: Int = 0,
    val category: String = ""
)

data class ResponseSources(
    val sources: List<Source> = listOf()
)

data class Source(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val url: String = "",
    val category: String = "",
    val language: String = "",
    val country: String = ""
)