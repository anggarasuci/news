package co.anggarasuci.news.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewLoadMoreScroll(
    val layoutManager: RecyclerView.LayoutManager,
    private val loadMore: () -> Unit
) :
    RecyclerView.OnScrollListener() {

    private var visibleThreshold = 1
    private var loaded = false
    private var lastVisibleItem = 0

    init {
        if (layoutManager is GridLayoutManager) {
            visibleThreshold *= layoutManager.spanCount
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return
        val totalItemCount = layoutManager.itemCount
        when (layoutManager) {
            is GridLayoutManager -> lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        }

        if (!loaded && totalItemCount <= lastVisibleItem + visibleThreshold) {
            loadMore.invoke()
            loaded = true
        }
    }

    fun setLoaded() {
        loaded = false
    }
}
