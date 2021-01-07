package co.anggarasuci.news.ui.webview

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import co.anggarasuci.news.R
import co.anggarasuci.news.base.BaseActivity
import kotlinx.android.synthetic.main.webview_activity.*

class WebViewActivity : BaseActivity() {
    private val url by lazy { intent?.getStringExtra(URL) ?: "" }

    override fun layoutRes() = R.layout.webview_activity
    override fun getScreenName() = javaClass.simpleName

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupView() {
        webview_pb?.max = PROGRESS_MAX
        webview?.apply {
            settings?.javaScriptEnabled = true
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    if (newProgress < PROGRESS_MAX) webview_pb?.progress = newProgress
                    else webview_pb?.visibility = View.INVISIBLE
                }
            }
        }
        webview?.loadUrl(url)
    }

    override fun setupViewEvent() { }

    override fun subscribeState() { }

    companion object {
        const val URL = ".url"
        const val PROGRESS_MAX = 100

        fun withActivityData(url: String = ""):
                Array<Pair<String, *>> {
            return arrayOf(URL to url)
        }
    }
}
