package co.anggarasuci.news.ui.article

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import co.anggarasuci.news.R
import co.anggarasuci.news.base.BaseActivity
import co.anggarasuci.news.data.model.Article
import co.anggarasuci.news.ui.article.ArticleViewModel.Event
import co.anggarasuci.news.ui.article.ArticleViewModel.State
import co.anggarasuci.news.util.RecyclerViewLoadMoreScroll
import co.anggarasuci.news.util.subscribeSingleLiveEvent
import kotlinx.android.synthetic.main.article_activity.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class ArticleActivity: BaseActivity() {
    private val _viewModel: ArticleViewModel by viewModel()
    private lateinit var _loadMoreScroll: RecyclerViewLoadMoreScroll
    private val _articleAdapter by lazy { ArticleAdapter { onItemClick(it) } }
    private val _sourceId by lazy { intent?.getStringExtra(SOURCE_ID) ?: "" }
    private val _source by lazy { intent?.getStringExtra(SOURCE) ?: "" }


    override fun getScreenName() = javaClass.simpleName

    override fun layoutRes() = R.layout.article_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel.onEventReceived(Event.OnCreate(_sourceId))
    }

    override fun setupView() {
        initAdapter()
    }

    override fun setupViewEvent() {

    }

    override fun subscribeState() {
        subscribeSingleLiveEvent(_viewModel.state) {
            when (it) {
                is State.Error -> toast(it.message)
                is State.ShowData -> onShowData(it.data, it.isFirstPage)
                is State.ShowLoading -> progress?.isVisible = it.isLoading
            }
        }
    }

    private fun onShowData(data: List<Article>, isFirstPage: Boolean) {
        _loadMoreScroll.setLoaded()
        when {
            isFirstPage && data.isNullOrEmpty() -> {
                _articleAdapter.clear()
                toast(getString(R.string.not_found)) }
            !isFirstPage && data.isNullOrEmpty() -> toast(getString(R.string.all_article_loaded))
            else ->  _articleAdapter.apply { if (isFirstPage) setNewData(data) else addData(data) }
        }
    }

    private fun initAdapter() {
        val mLayoutManager = LinearLayoutManager(this)
        _loadMoreScroll = RecyclerViewLoadMoreScroll(mLayoutManager) {
            _viewModel.onEventReceived(Event.OnLoadMore) }
        rv?.apply {
            layoutManager = mLayoutManager
            adapter = _articleAdapter
            setHasFixedSize(true)
            addOnScrollListener(_loadMoreScroll)
        }
    }

    private fun onItemClick(url: String) {

    }

    companion object {
        const val SOURCE_ID = ".sourceId"
        const val SOURCE = ".source"

        fun withActivityData(sourceId: String, source: String):
                Array<Pair<String, *>> {
            return arrayOf(
                SOURCE_ID to sourceId,
                SOURCE to source
            )
        }
    }
}