package com.example.kasirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirapp.databinding.ActivityUpdateMejaBinding

class UpdateMeja : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateMejaBinding
    private lateinit var mejaViewModel: MejaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMejaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.updateTableIbBack.setOnClickListener {
            startActivity(Intent(this, DataMeja::class.java ))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        val noMeja = binding.updateTableEtNo


        noMeja.setText(intent.getStringExtra("noMeja"))

        mejaViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MejaViewModel::class.java]

        binding.updateTableBtnSave.setOnClickListener {
            val newNoMeja = noMeja.text.toString()

            when{

                newNoMeja.isEmpty() -> alert()

                else -> {
                    val updatedMeja = Meja(number = newNoMeja.toInt())
                    updatedMeja.id = id
                    mejaViewModel.updateTable(updatedMeja)
                    startActivity(Intent(this, DataMeja::class.java))
                    finish()
                }
            }
        }
    }

    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}