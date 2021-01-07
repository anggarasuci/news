package co.anggarasuci.news.ui.source

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.anggarasuci.news.R
import co.anggarasuci.news.base.BaseActivity
import co.anggarasuci.news.data.model.Source
import co.anggarasuci.news.util.RecyclerViewLoadMoreScroll
import com.afollestad.materialdialogs.utils.MDUtil.getStringArray
import kotlinx.android.synthetic.main.sources_activity.*
import co.anggarasuci.news.ui.source.SourcesViewModel.State
import co.anggarasuci.news.ui.source.SourcesViewModel.Event
import co.anggarasuci.news.util.subscribeSingleLiveEvent
import org.koin.android.viewmodel.ext.android.viewModel

class SourcesActivity: BaseActivity() {
    private val _viewModel: SourcesViewModel by viewModel()
    private val _categoryAdapter by lazy { CategoryAdapter { onCategoryItemClick(it) } }
    private lateinit var _loadMoreScroll: RecyclerViewLoadMoreScroll
    private val _sourceAdapter by lazy { SourcesAdapter { onSourceItemClick(it) } }


    override fun getScreenName() = javaClass.simpleName

    override fun layoutRes() = R.layout.sources_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel.onEventReceived(Event.OnCreate)
    }

    override fun setupView() {
        initCategoryAdapter()
        initSourceAdapter()
    }

    override fun setupViewEvent() {

    }

    override fun subscribeState() {
        subscribeSingleLiveEvent(_viewModel.state) {
            when (it) {
                is State.Error -> { }
                is State.ShowData -> onShowData(it.data)
                is State.ShowLoading -> { }
            }
        }
    }

    private fun onShowData(data: List<Source>) {
        _loadMoreScroll.setLoaded()
        _sourceAdapter.setNewData(data)
    }

    private fun initCategoryAdapter() {
        val mLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        tab_rv?.apply {
            layoutManager = mLayoutManager
            adapter = _categoryAdapter
        }
        _categoryAdapter.setData(getStringArray(R.array.categories_array).toList())
    }

    private fun initSourceAdapter() {
        val mLayoutManager = LinearLayoutManager(this)
        _loadMoreScroll = RecyclerViewLoadMoreScroll(mLayoutManager) {  }
        rv?.apply {
            layoutManager = mLayoutManager
            adapter = _sourceAdapter
            setHasFixedSize(true)
            addOnScrollListener(_loadMoreScroll)
        }
    }

    private fun onCategoryItemClick(category: String) {
        _viewModel.onEventReceived(Event.OnItemCategoryClick(category))
    }

    private fun onSourceItemClick(id: String) {

    }
}