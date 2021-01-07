package co.anggarasuci.news.ui.source

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.anggarasuci.news.R
import co.anggarasuci.news.base.BaseActivity
import com.afollestad.materialdialogs.utils.MDUtil.getStringArray
import kotlinx.android.synthetic.main.sources_activity.*

class SourcesActivity: BaseActivity() {

    private val _categoryAdapter by lazy {
        CategoryAdapter { onCategoryItemClick(it) }
    }

    override fun getScreenName() = javaClass.simpleName

    override fun layoutRes() = R.layout.sources_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setupView() {
        initCategoryAdapter()
    }

    override fun setupViewEvent() {

    }

    override fun subscribeState() {

    }

    private fun initCategoryAdapter() {
        val mLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        tab_rv?.apply {
            layoutManager = mLayoutManager
            adapter = _categoryAdapter
        }
        _categoryAdapter.setData(getStringArray(R.array.categories_array).toList())
    }

    private fun onCategoryItemClick(category: String) {

    }
}