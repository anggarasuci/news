package co.anggarasuci.news.di

import org.koin.dsl.module

val useCaseModule = module {
}

val repositoryModule = module {

}

val dataSourceModule = module {

}

val otherModule = module {}

val domainDataModule = listOf(useCaseModule, repositoryModule, dataSourceModule, otherModule)
