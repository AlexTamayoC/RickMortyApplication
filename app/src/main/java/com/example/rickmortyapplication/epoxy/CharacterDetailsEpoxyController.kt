package com.example.rickmortyapplication.epoxy

import android.media.Image
import com.airbnb.epoxy.EpoxyController
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.databinding.ModelCharacterDetailsDataPointBinding
import com.example.rickmortyapplication.databinding.ModelCharacterDetailsHeaderBinding
import com.example.rickmortyapplication.databinding.ModelCharacterDetailsImageBinding
import com.example.rickmortyapplication.epoxy.response.Character
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value){
            field=value
            if(field){
                requestModelBuild()
            }
        }

    var characterResponse: Character? = null
        set(value){
            field = value
            if (field != null){
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (characterResponse == null){
            //TODO error state
            return
        }

        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)

        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        DataPointEpoxyModel(
            title= "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        ).id("data_point_2").addTo(this)
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ): ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image){

        override fun ModelCharacterDetailsImageBinding.bind(){
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title : String,
        val description:String
    ): ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point){

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header){

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status
        }
    }
}