package co.anggarasuci.news.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

interface ResponseBodyInterface {
    fun toErrorJson(status: Int, message: String): String
}

/**
 * should be adjustment based on from json object api
 */
data class Response<T>(
    val message: String = "",
    val code: String = "",
    val status: String = "",

    @SerializedName("sources", alternate = ["articles"])
//    @SerializedName("sources")
    val content: T,


    val totalResults: Int = 0
)

data class ErrorModel(
    val code: String = "",
    val message: String = "",
    val reasons: Map<String, String> = emptyMap()
)

data class ResponseError(
    val rid: String = "",
    val status: Int = 0,
    val error: ErrorModel = ErrorModel()
): ResponseBodyInterface {
    override fun toErrorJson(status: Int, message: String): String {
        return Gson().toJson(
            ResponseError(
                status = status,
                error = ErrorModel(message = message)
            )
        )
    }
}