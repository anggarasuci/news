package co.anggarasuci.news.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.anggarasuci.news.base.BaseViewModel
import co.anggarasuci.news.data.model.Source
import co.anggarasuci.news.domain.GetSourcesUseCase
import co.anggarasuci.news.domain.Result
import co.anggarasuci.news.util.wrapper.EventWrapper
import kotlinx.coroutines.launch

class SourcesViewModel(private val getSourcesUseCase: GetSourcesUseCase) : BaseViewModel() {
    sealed class Event {
        object OnCreate : Event()
        data class OnItemCategoryClick(val category: String): Event()
    }

    sealed class State {
        data class Error(val message: String, val code: String) : State()
        data class ShowData(val data: List<Source>) : State()
        data class ShowLoading(val isLoading: Boolean): State()
    }

    private val _state = MutableLiveData<EventWrapper<State>>()
    val state: LiveData<EventWrapper<State>> = _state

    private var _category = ""

    fun onEventReceived(event: Event) {
        when (event) {
            Event.OnCreate -> handleOnCreate()
            is Event.OnItemCategoryClick -> handleCategoryClick(event.category)
        }
    }

    private fun handleOnCreate() = launch {
        getListSources()
    }

    private fun handleCategoryClick(category: String) = launch {
        _category = category
        getListSources()
    }

    private suspend fun getListSources() {
        setState(State.ShowLoading(true))
        val params = getSourcesUseCase.createParams(category = _category)
        when (val result = getSourcesUseCase.execute(params)) {
            is Result.Success -> setState(State.ShowData(result.value.sources))
            is Result.Error -> setState(State.Error(result.errorMessage, result.codeMessage))
        }
        setState(State.ShowLoading(false))
    }

    private fun setState(state: State) {
        _state.value = EventWrapper(state)
    }
}
