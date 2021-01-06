package co.anggarasuci.news.network

import co.anggarasuci.news.domain.Result
import co.anggarasuci.news.data.model.Response
import kotlinx.coroutines.Deferred

interface ResponseParserAdapter {
    fun <T> create(): ResponseParser<T>
}

/**
 * should be adjustment by response api
 */
class ResponseParser<T> {
    suspend fun parseResult(result: Deferred<Response<T>>): Result<Response<T>> {
        val r = result.await()
        @Suppress("SENSELESS_COMPARISON")
        return when {
            (r.status.contains("ok")) -> Result.Success(r)
            r.status.contains("error") -> Result.Error(r.message, r.code)
            else -> Result.Error("Unknown Error", "", emptyMap())
        }
    }

    companion object {
        val Factory: ResponseParserAdapter = object : ResponseParserAdapter {
            override fun <TT> create(): ResponseParser<TT> {
                return ResponseParser()
            }
        }
    }
}