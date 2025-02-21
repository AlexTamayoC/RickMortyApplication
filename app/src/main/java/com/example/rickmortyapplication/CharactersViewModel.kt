package com.example.rickmortyapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.example.rickmortyapplication.characters.CharactersDataSourceFactory
import com.example.rickmortyapplication.epoxy.response.Character
import com.example.rickmortyapplication.repository.CharactersRepository

class CharactersViewModel: ViewModel(){

    private val repository = CharactersRepository()
    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()

        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)
    val charactersPagedListLiveData: LiveData<PagedList<Character>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()
}