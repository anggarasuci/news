package co.anggarasuci.news.di

import co.anggarasuci.news.ui.source.SourcesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SourcesViewModel(getSourcesUseCase = get())
    }
}

val appModules = listOf(viewModelModule)