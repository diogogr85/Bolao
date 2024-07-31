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

private const val BASE_URL = "base_url"

val networkModules = module {
//    single(named("mockEngineResponse")) { mockEngine }
//    single { HttpClientBuilder(get(named("mockEngineResponse")), host = BASE_URL).build() }
    single { HttpClientBuilder(host = BASE_URL).build() }
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

private const val jsonResponse = "{\"matches\":[{\"team_home\":\"Alemanha\",\"team_guest\":\"Brasil\",\"team_home_score\":8,\"team_guest_score\":1,\"date\":\"2024-08-07 09:00:00\",\"venue\":\"Estadio estadio\",\"group\":\"A\",\"round\":\"fase de groupo\"},{\"team_home\":\"Inglaterra\",\"team_guest\":\"França\",\"date\":\"2024-08-07 09:00:00\",\"venue\":\"Estadio estadio\",\"group\":\"B\",\"round\":\"fase de groupo\"},{\"team_home\":\"EUA\",\"team_guest\":\"Canadá\",\"team_home_score\":2,\"team_guest_score\":1,\"date\":\"2024-08-07 09:00:00\",\"venue\":\"Estadio estadio\",\"group\":\"D\",\"round\":\"fase de groupo\"}]}"
private val mockEngine = MockEngine {
    respond(
        content = ByteReadChannel(jsonResponse),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
    )
}