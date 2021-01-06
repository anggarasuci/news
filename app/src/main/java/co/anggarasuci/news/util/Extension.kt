package co.anggarasuci.news.util

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import co.anggarasuci.news.network.HttpStatus

@SuppressLint("HardwareIds")
fun Context.getDeviceId(): String {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}

fun Int.isHttp2XX(): Boolean { return (this in HttpStatus.Http2xx) }
fun Int.isHttp4XX(): Boolean { return (this in HttpStatus.Http4xx) }
fun Int.isHttp5XX(): Boolean { return (this in HttpStatus.Http5xx) }
fun Int.isGatewayTimeout(): Boolean { return this == HttpStatus.GATEWAY_TIMEOUT }
fun Int.isUnauthorized(): Boolean { return this == HttpStatus.UNAUTHORIZED}
fun Int.isServerUnreachable() : Boolean { return this == HttpStatus.NETWORK_READ_TIMEOUT_ERROR }
fun Int.isServiceUnavailable() : Boolean { return this == HttpStatus.SERVICE_UNAVAILABLE }
fun Int.isHttpMultiStatus() : Boolean { return this == HttpStatus.MULTI_STATUS }
fun Int.formatToTimer() = String.format("%02d", this)

//fun Double.toFormattedMoney(): String = Converters.toFormattedMoney(this)
//fun Float.toFormattedMoney(): String = Converters.toFormattedMoney(this)
//fun Float.toFormattedMoneyWithPrefix(): String = Converters.toFormattedMoney(this, true, Values.DefaultMoneyPrefix)
//fun Double.toMoneyWithPrefix(): String = String.format("%.0f", this).toMoneyWithPrefix()
