package com.tutorial.rickmortyapp.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tutorial.rickmortyapp.model.Personaje

@Composable
fun PersonajesScreen(
    viewModel: PersonajesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Log.d("Cant.  items", "${state.size}")
    LazyColumn {
        items(state) { personaje ->
            PersonajeCard(personaje)
        }
    }

}

@Composable
fun PersonajeCard(personaje: Personaje, modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column {
            Row {
                Surface(
                    modifier.size(120.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                ) {
                    AsyncImage(
                        model = personaje.image,
                        contentDescription = personaje.name,
                        contentScale = ContentScale.FillBounds
                    )
                }
                Column(
                    modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                ) {
                    Text(text = personaje.name, style = MaterialTheme.typography.titleLarge)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val color = when (personaje.status) {
                            "Alive" -> Color.Green
                            "Dead" -> Color.Red
                            else -> Color.Gray
                        }
                        Box(
                            modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(12.dp)
                        )
                        Text(
                            text = "${personaje.status} - ${personaje.species}",
                            style = MaterialTheme.typography.titleSmall
                        )

                    }

                }
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
                            Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Mas Infomracion"
                    )

                }

            }
            if (expanded) {
                Row(modifier.padding(16.dp)) {
                    Column {
                        Text(text = "Ultima aparicion", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = personaje.location.name,
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }
                }
            }
        }
    }

}