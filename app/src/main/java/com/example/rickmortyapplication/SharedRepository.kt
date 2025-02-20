package com.example.rickmortyapplication

import com.example.rickmortyapplication.epoxy.response.Character
import com.example.rickmortyapplication.network.NetworkLayer

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }

        if(!request.isSuccessful){
            return null
        }

        return request.body
    }
}
