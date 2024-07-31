package com.tutorial.rickmortyapp.repository

import com.tutorial.rickmortyapp.api.PersonajesApi
import com.tutorial.rickmortyapp.model.ListaPersonajes
import javax.inject.Inject

class PersonajesRepository @Inject constructor(
    private val personajesApi: PersonajesApi
) {
    suspend fun getPersonajes(): ListaPersonajes{
        return personajesApi.getPersonajes()
    }
}