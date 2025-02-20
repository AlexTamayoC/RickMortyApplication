package com.example.rickmortyapplication

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
