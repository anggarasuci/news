package co.anggarasuci.news.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.anggarasuci.news.base.BaseViewModel
import co.anggarasuci.news.data.model.Article
import co.anggarasuci.news.domain.GetArticlesUseCase
import co.anggarasuci.news.domain.Result
import co.anggarasuci.news.util.wrapper.EventWrapper
import kotlinx.coroutines.launch

class ArticleViewModel(private val getArticlesUseCase: GetArticlesUseCase) : BaseViewModel() {
    sealed class Event {
        data class OnCreate(val sourceId: String) : Event()
        object OnLoadMore: Event()
        data class OnFilter(val keyword: String) : Event()
        object OnRefresh : Event()
    }

    sealed class State {
        data class Error(val message: String, val code: String) : State()
        data class ShowData(val data: List<Article>, val isFirstPage: Boolean) : State()
        data class ShowLoading(val isLoading: Boolean): State()
    }

    private val _state = MutableLiveData<EventWrapper<State>>()
    val state: LiveData<EventWrapper<State>> = _state

    private var _currentPage = 1
    private var _keyword = ""
    private var _sourceId = ""

    fun onEventReceived(event: Event) {
        when (event) {
            is Event.OnCreate -> handleOnCreate(event.sourceId)
            Event.OnLoadMore -> handleLoadMore()
            is Event.OnFilter -> handleOnFilter(event.keyword)
            Event.OnRefresh -> handleOnRefresh()
        }
    }

    private fun handleOnCreate(sourceId: String) = launch {
        _sourceId = sourceId
        _keyword = ""
        getListArticle(true)
    }

    private fun handleLoadMore() = launch {
        getListArticle(false)
    }

    private fun handleOnFilter(keyword: String) = launch {
        _keyword = keyword
        getListArticle(true)
    }

    private fun handleOnRefresh() = launch {
        getListArticle(true)
    }

    private suspend fun getListArticle(isFirstPage: Boolean) {
        setState(State.ShowLoading(true))
        if (isFirstPage) _currentPage = 1
        val params = getArticlesUseCase.createParams(
            page = _currentPage,
            keyword = _keyword,
            sourceId = _sourceId,
            rows = 3)
        when (val result = getArticlesUseCase.execute(params)) {
            is Result.Success -> {
                val articles = if (!result.value.articles.isNullOrEmpty()) result.value.articles else emptyList()
                if (!articles.isNullOrEmpty()) _currentPage++
                setState(State.ShowData(articles, isFirstPage))
            }
            is Result.Error -> setState(State.Error(result.errorMessage, result.codeMessage))
        }
        setState(State.ShowLoading(false))
    }

    private fun setState(state: State) {
        _state.value = EventWrapper(state)
    }
}
