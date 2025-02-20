package com.example.rickmortyapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<AppCompatTextView>(R.id.nameTextView)
        val headerImageView = findViewById<AppCompatImageView>(R.id.headerImageView)
        val aliveTextView = findViewById<AppCompatTextView>(R.id.aliveTextView)
        val originTextView = findViewById<AppCompatTextView>(R.id.originTextView)
        val speciesTextView = findViewById<AppCompatTextView>(R.id.speciesTextView)

        viewModel.refreshCharacter(10)
        viewModel.characterByIdLiveData.observe(this){ response ->
            if (response == null){
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccesful network call!!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            nameTextView.text = response.name
            aliveTextView.text = response.status
            originTextView.text = response.origin.name
            speciesTextView.text=response.species
            Picasso.get().load(response.image).into(headerImageView)
        }
    }
}