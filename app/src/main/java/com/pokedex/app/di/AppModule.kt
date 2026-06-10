package com.pokedex.app.di

import com.pokedex.app.data.remote.PokeApiService
import com.pokedex.app.data.repository.PokemonRepositoryImpl
import com.pokedex.app.domain.repository.PokemonRepository
import com.pokedex.app.domain.usecase.GetPokemonListUseCase
import com.pokedex.app.presentation.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(PokeApiService::class.java) }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { GetPokemonListUseCase(get()) }
}

val viewModelModule = module {
    viewModel { PokemonViewModel(get()) }
}
