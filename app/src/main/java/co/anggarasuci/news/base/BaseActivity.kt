package co.anggarasuci.news.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    @LayoutRes
    protected abstract fun layoutRes(): Int

    abstract fun setupView()
    abstract fun setupViewEvent()
    abstract fun subscribeState()
    abstract fun getScreenName(): String

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())

        setupView()
        setupViewEvent()
        subscribeState()
    }
}