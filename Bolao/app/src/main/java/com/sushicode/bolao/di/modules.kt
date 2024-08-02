package com.sushicode.bolao.di

import com.sushicode.bolao.data.network.HttpClientBuilder
import com.sushicode.bolao.data.network.apis.MatchesApi
import com.sushicode.bolao.data.network.apis.MatchesApiImpl
import com.sushicode.bolao.domain.repositories.MatchesRepository
import com.sushicode.bolao.domain.repositories.MatchesRepositoryImpl
import com.sushicode.bolao.domain.useCases.MatchesUseCase
import com.sushicode.bolao.domain.useCases.MatchesUseCaseImpl
import com.sushicode.bolao.ui.viewModels.MatchesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModules = module {
    single(named("base-url")) { "66aad0e7636a4840d7c89baa.mockapi.io/sushicode/bolao" }
    single { HttpClientBuilder(baseUrl = get(named("base-url"))) }
    factory<MatchesApi> { MatchesApiImpl(get()) }
}

val repositoriesModules = module {
    single(named("IODispatcher")) { Dispatchers.IO }
    factory<MatchesRepository> { MatchesRepositoryImpl(get(), get(named("IODispatcher"))) }
}

val useCaseModules = module {
    factory<MatchesUseCase> { MatchesUseCaseImpl(get()) }
}

val presentationModules = module {
    viewModel<MatchesViewModel> { MatchesViewModel(get()) }
}