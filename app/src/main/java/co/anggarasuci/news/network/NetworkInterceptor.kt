package co.anggarasuci.news.network

import android.content.Context
import co.anggarasuci.news.data.model.ResponseBodyInterface
import okhttp3.*
import okhttp3.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.nio.charset.Charset

interface NetworkInterceptorCallback {
    fun onUnauthorized()
    fun getRequestExceptionMessage(): String
    fun getRequestTimeoutMessage(): String
    fun getNetworkUnavailableMessage(): String
    fun getResponseBodyModel(): ResponseBodyInterface
}

class NetworkInterceptor(
    private val context: Context,
    private val action: NetworkInterceptorCallback
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)
            return if (response.isSuccessful) {
                response
            } else {
                if (response.code == HttpStatus.UNAUTHORIZED) {
                    action.onUnauthorized()
                }

                var responseBodyString =
                    response.body?.source()?.readString(Charset.defaultCharset())
                val message = action.getRequestExceptionMessage()

                if (response.code == HttpStatus.FORBIDDEN) {
                    responseBodyString =
                        action.getResponseBodyModel().toErrorJson(HttpStatus.FORBIDDEN, message)
                }

                //handling when service return html response
                if (responseBodyString.isNullOrEmpty() || !responseBodyString.trim().startsWith("{")) {
                    responseBodyString = action.getResponseBodyModel()
                        .toErrorJson(HttpStatus.GATEWAY_TIMEOUT, action.getRequestTimeoutMessage())
                }

                generateCustomBuild(
                    response.newBuilder(), request,
                    HttpStatus.MULTI_STATUS,
                    message, responseBodyString
                )
            }
        } catch (ex: IOException) {
            //build valid response error when no internet connection available
            //https://en.wikipedia.org/wiki/List_of_HTTP_status_codes#5xx_Server_errors
            //HttpStatus: 598 --> (Informal convention) Network read timeout error
            //HttpStatus: 207 --> to avoid throwing exception from retrofit and crashing the apps
            var status = HttpStatus.NETWORK_READ_TIMEOUT_ERROR
            var message = action.getRequestTimeoutMessage()

            if (!NetworkUtilities().isConnected(context)) {
                status = HttpStatus.SERVICE_UNAVAILABLE
                message = action.getNetworkUnavailableMessage()
            }

            return generateCustomBuild(
                Response.Builder(),
                request,
                HttpStatus.MULTI_STATUS,
                message,
                action.getResponseBodyModel().toErrorJson(status, message)
            )
        }
    }

    private fun generateCustomBuild(
        builder: Response.Builder,
        request: Request,
        statusHeader: Int,
        builderMessage: String,
        body: String
    ): Response {

        val responseBody = body.toResponseBody("application/json".toMediaTypeOrNull())

        builder
            .code(statusHeader) //to avoid throwing exception from retrofit and crashing the apps
            .protocol(Protocol.HTTP_1_1)
            .request(request)
            .message(builderMessage)
            .body(responseBody)

        return builder.build()
    }
}

