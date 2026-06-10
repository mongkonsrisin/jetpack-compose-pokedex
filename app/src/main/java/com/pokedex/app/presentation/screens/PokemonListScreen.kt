package com.pokedex.app.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pokedex.app.presentation.viewmodel.PokemonViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        
        LazyColumn {
            items(state.value.pokemonList) { pokemon ->
                Text(text = pokemon.name)
            }
        }

        state.value.error?.let { error ->
            Text(text = error, modifier = Modifier.align(Alignment.Center))
        }
    }
}
