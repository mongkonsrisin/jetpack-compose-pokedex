package com.pokedex.app.presentation.pokemon_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokedex.app.domain.model.Pokemon

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel, onPokemonClick: (Pokemon) -> Unit) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pokedex") }) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(text = "Error: ${state.error}")
            } else {
                LazyColumn {
                    items(state.pokemon) { pokemon ->
                        PokemonItem(pokemon = pokemon, onClick = { onPokemonClick(pokemon) })
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = pokemon.name.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.titleLarge)
        }
    }
}
