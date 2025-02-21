package com.example.rickmortyapplication.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.databinding.ModelCharacterListItemBinding
import com.example.rickmortyapplication.epoxy.response.Character
import com.squareup.picasso.Picasso

class CharacterListPagingEpoxyController : PagedListEpoxyController<Character>(){

    override fun buildItemModel(
        currentPosition: Int,
        item: Character?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){
        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }

    }
}