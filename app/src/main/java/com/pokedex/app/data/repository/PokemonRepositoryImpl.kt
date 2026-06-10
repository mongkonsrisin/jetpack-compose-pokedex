package com.pokedex.app.data.repository

import com.pokedex.app.data.remote.PokeApiService
import com.pokedex.app.domain.model.Pokemon
import com.pokedex.app.domain.model.PokemonDetail
import com.pokedex.app.domain.repository.PokemonRepository

class PokemonRepositoryImpl(private val apiService: PokeApiService) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Result<List<Pokemon>> {
        return try {
            val response = apiService.getPokemonList(limit, offset)
            val pokemonList = response.results.map { dto ->
                val id = dto.url.split("/").dropLast(1).last().toInt()
                Pokemon(name = dto.name, url = dto.url, id = id)
            }
            Result.success(pokemonList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetail(name: String): Result<PokemonDetail> {
        return try {
            val dto = apiService.getPokemonDetail(name)
            val detail = PokemonDetail(
                id = dto.id,
                name = dto.name,
                height = dto.height,
                weight = dto.weight,
                types = dto.types.map { it.type.name },
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${dto.id}.png"
            )
            Result.success(detail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
