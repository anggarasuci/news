package co.anggarasuci.news.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun setupView()
    abstract fun setupViewEvent()
    abstract fun subscribeState()
}