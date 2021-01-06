package co.anggarasuci.news.domain

sealed class Result<out T : Any> {
    data class Success<T : Any>(val value: T) : Result<T>()
    data class Error(val errorMessage: String, val codeMessage: String = "", val reason: Map<String, String> = emptyMap()) : Result<Nothing>()
}

abstract class UseCase<SuccessType : Any, in Params> {

    abstract suspend fun build(params: Params?): Result<SuccessType>

    suspend fun execute(params: Params? = null): Result<SuccessType> {
        return build(params)
    }
}