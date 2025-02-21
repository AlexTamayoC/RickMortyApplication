package com.example.rickmortyapplication.characters


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickmortyapplication.CharactersViewModel
import com.example.rickmortyapplication.Constants
import com.example.rickmortyapplication.MainActivity
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.epoxy.CharacterListPagingEpoxyController
import com.example.rickmortyapplication.epoxy.response.Character

class CharacterListActivity : AppCompatActivity(){

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        viewModel.charactersPagedListLiveData.observe(this){ pagedList ->
            epoxyController.submitList(pagedList)
        }

        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }

    private fun onCharacterSelected(characterId: Int){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }
}