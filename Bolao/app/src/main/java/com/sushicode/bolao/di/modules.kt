package com.sushicode.bolao.di

import com.sushicode.bolao.data.network.HttpClientBuilder
import com.sushicode.bolao.data.network.apis.MatchesApi
import com.sushicode.bolao.data.network.apis.MatchesApiImpl
import com.sushicode.bolao.domain.repositories.MatchesRepository
import com.sushicode.bolao.domain.repositories.MatchesRepositoryImpl
import com.sushicode.bolao.domain.useCases.MatchesUseCase
import com.sushicode.bolao.domain.useCases.MatchesUseCaseImpl
import com.sushicode.bolao.ui.viewModels.MatchesViewModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModules = module {
    single { HttpClientBuilder() }
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