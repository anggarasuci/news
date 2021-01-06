package co.anggarasuci.news.util

import co.anggarasuci.news.BuildConfig
import com.orhanobut.hawk.Hawk
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LocalCache<T>(private val key: String, private val defaultValue: T) : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return if (defaultValue != null)
            Hawk.get(BuildConfig.APPLICATION_ID + key, defaultValue)
        else
            Hawk.get(BuildConfig.APPLICATION_ID + key) as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        Hawk.put(BuildConfig.APPLICATION_ID + key, value)
    }

}

fun removeCache(key: String) {
    Hawk.delete(BuildConfig.APPLICATION_ID + key)
}

fun removeAllCache() {
    Hawk.deleteAll()
}
