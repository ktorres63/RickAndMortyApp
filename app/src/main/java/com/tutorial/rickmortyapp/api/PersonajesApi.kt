package com.tutorial.rickmortyapp.api

import com.tutorial.rickmortyapp.model.ListaPersonajes
import retrofit2.http.GET

interface PersonajesApi {
    @GET("character")
    suspend fun getPersonajes(): ListaPersonajes
}