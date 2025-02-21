package com.example.rickmortyapplication.repository

import com.example.rickmortyapplication.epoxy.response.Character
import com.example.rickmortyapplication.epoxy.response.GetCharactersPageResponse
import com.example.rickmortyapplication.network.NetworkLayer

class CharactersRepository {
    suspend fun getCharactersPage(pageIndex: Int): GetCharactersPageResponse?{
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if(request.failed || !request.isSuccessful){
            return null
        }

        return request.body
    }
}