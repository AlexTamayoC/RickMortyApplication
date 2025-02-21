package com.example.rickmortyapplication.network

import com.example.rickmortyapplication.epoxy.response.Character
import com.example.rickmortyapplication.epoxy.response.GetCharactersPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId : Int
    ): Response<Character>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ) : Response<GetCharactersPageResponse>
}