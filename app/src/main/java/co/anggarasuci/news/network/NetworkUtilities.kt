package co.anggarasuci.news.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

@Suppress("DEPRECATION")
class NetworkUtilities {
    @SuppressLint("MissingPermission")
    fun isConnected(context: Context): Boolean {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //When on API 21+ need to use getAllNetworks, else fall base to GetAllNetworkInfo
        //https://developer.android.com/reference/android/net/ConnectivityManager.html#getAllNetworks()
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            for (network in cm.allNetworks) {
                try {
                    val capabilities = cm.getNetworkCapabilities(network) ?: continue

                    //check to see if it has the internet capability
                    if (!capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) continue

                    //if on 23+ then we can also check validated
                    //Indicates that connectivity on this network was successfully validated.
                    //this means that you can be connected to wifi and has internet
                    if (android.os.Build.VERSION.SDK_INT >= 23 && !capabilities.hasCapability(
                            NetworkCapabilities.NET_CAPABILITY_VALIDATED)) continue

                    val info = cm.getNetworkInfo(network)

                    if (info == null || !info.isAvailable) continue

                    if (info.isConnected) return true
                } catch (ex: Exception) {
                    //there is a possibility, but don't worry
                }
            }
        } else {
            //pragma warning disable CS0618 // Type or member is obsolete
            for (info in cm.allNetworkInfo)
            //pragma warning restore CS0618 // Type or member is obsolete
            {
                if (info == null || !info.isAvailable) continue

                if (info.isConnected) return true
            }
        }

        return false
    }
}