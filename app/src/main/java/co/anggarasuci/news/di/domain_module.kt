package co.anggarasuci.news.di

import co.anggarasuci.news.data.remote.NewsRemoteDataSource
import co.anggarasuci.news.data.repository.NewsRepository
import co.anggarasuci.news.domain.GetArticlesUseCase
import co.anggarasuci.news.domain.GetSourcesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetSourcesUseCase(newsRepository = get()) }
    single { GetArticlesUseCase(newsRepository = get()) }
}

val repositoryModule = module {
    single { NewsRepository(newsRemoteDataSource = get()) }
}

val dataSourceModule = module {
    single { NewsRemoteDataSource(newsService = get()) }
}

val otherModule = module {}

val domainDataModule = listOf(useCaseModule, repositoryModule, dataSourceModule, otherModule)
