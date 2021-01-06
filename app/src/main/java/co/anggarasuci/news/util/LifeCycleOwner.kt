package co.anggarasuci.news.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import co.anggarasuci.news.util.wrapper.EventWrapper

inline fun <T>LifecycleOwner.subscribe(liveData: LiveData<T>, crossinline block: (T?) -> Unit) {
    liveData.observe(this, Observer {
        block(it)
    })
}

inline fun <T>LifecycleOwner.subscribeFilterNull(liveData: LiveData<T>, crossinline block: (T) -> Unit) {
    liveData.observe(this, Observer {
        it?.let { value -> block(value) }
    })
}

inline fun <T>LifecycleOwner.subscribeSingleLiveEvent(liveData: LiveData<EventWrapper<T>>, crossinline onEventUnhandled: (T) -> Unit) {
    liveData.observe(this, Observer { it?.getEventIfNotHandled()?.let(onEventUnhandled) })
}
