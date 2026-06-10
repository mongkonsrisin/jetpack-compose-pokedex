package com.pokedex.app.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.app.domain.model.Pokemon
import com.pokedex.app.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemon: List<Pokemon> = emptyList(),
    val error: String? = null
)

class PokemonListViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {
    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getPokemonListUseCase().onSuccess { list ->
                _state.value = _state.value.copy(isLoading = false, pokemon = list)
            }.onFailure { e ->
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
