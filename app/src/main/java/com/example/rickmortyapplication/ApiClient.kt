package com.example.rickmortyapplication

import com.example.rickmortyapplication.Character
import retrofit2.Response

class ApiClient (
    private val rickAndMortyService: RickAndMortyService
){

    suspend fun getCharacterById(characterId: Int): Response<Character>{
        return rickAndMortyService.getCharacterById(characterId)
    }
}