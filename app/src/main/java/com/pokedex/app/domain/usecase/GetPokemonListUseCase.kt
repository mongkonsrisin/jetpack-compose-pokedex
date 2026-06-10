package com.pokedex.app.domain.usecase

import com.pokedex.app.domain.repository.PokemonRepository
import com.pokedex.app.domain.model.Pokemon

class GetPokemonListUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): Result<List<Pokemon>> {
        return repository.getPokemonList(limit, offset)
    }
}
