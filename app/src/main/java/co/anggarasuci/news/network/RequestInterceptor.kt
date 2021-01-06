package co.anggarasuci.news.network

import co.anggarasuci.news.util.Constant
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        return chain.proceed(
            builder
                .addHeader("X-Api-Key", Constant.ApiKey)
                .build()
        )
    }
}
