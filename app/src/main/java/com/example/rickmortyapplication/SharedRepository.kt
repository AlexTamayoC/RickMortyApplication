package com.example.rickmortyapplication

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if(request.isSuccessful){
            return request.body()!!
        }

        return null
    }
}
