package com.example.rickmortyapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickmortyapplication.epoxy.CharacterDetailsEpoxyController
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.characterByIdLiveData.observe(this){ response ->

            epoxyController.characterResponse = response
            if (response == null){
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccesful network call!!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)
        viewModel.refreshCharacter(characterId = id)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}