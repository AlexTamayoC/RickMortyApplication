package com.example.rickmortyapplication.characters

import androidx.paging.DataSource
import com.example.rickmortyapplication.epoxy.response.Character
import com.example.rickmortyapplication.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory (
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): DataSource.Factory<Int, Character>(){

    override fun create(): DataSource<Int, Character>{
        return CharactersDataSource(coroutineScope, repository)
    }
}