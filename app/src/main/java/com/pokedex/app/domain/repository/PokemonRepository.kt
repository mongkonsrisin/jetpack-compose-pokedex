package com.pokedex.app.domain.repository

import com.pokedex.app.domain.model.Pokemon
import com.pokedex.app.domain.model.PokemonDetail

interface PokemonRepository {
    async suspend fun getPokemonList(limit: Int, offset: Int): Result<List<Pokemon>>
    async suspend fun getPokemonDetail(name: String): Result<PokemonDetail>
}
