package com.example.kasirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirapp.databinding.ActivityDataMenuBinding

class DataMenu : AppCompatActivity() {

    private lateinit var binding : ActivityDataMenuBinding
    private lateinit var menuViewModel: MenuViewModel
    private var foods = ArrayList<Food>()
    private var beverages = ArrayList<Beverage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        menuViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MenuViewModel::class.java]

        initFoodRv()
        initBeverageRv()

        binding.dataMenuIbBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataMenuFabAdd.setOnClickListener {
            startActivity(Intent(this, AddMenu::class.java))
            finish()
        }
    }

    private fun initFoodRv(){


        val Adapterfood = AdapterFood(this, foods, menuViewModel)

        val rvFood = binding.dataMenuRvMakanan
        rvFood.setHasFixedSize(true)
        rvFood.layoutManager = LinearLayoutManager(this)
        rvFood.adapter = Adapterfood

        menuViewModel.getFood.observe(this) { list ->
            list?.let {
                foods = it as ArrayList<Food>
                Adapterfood.updateFood(foods)
            }
        }
    }

    private fun initBeverageRv(){

        val Adapterbeverage = AdapterBeverage(this, beverages, menuViewModel)

        val rvBeverage = binding.dataMenuRvMinuman
        rvBeverage.setHasFixedSize(true)
        rvBeverage.layoutManager = LinearLayoutManager(this)
        rvBeverage.adapter = Adapterbeverage

        menuViewModel.getBeverage.observe(this) { list ->
            list?.let {
                beverages = it as ArrayList<Beverage>
                Adapterbeverage.updateBeverage(beverages)
            }
        }
    }
}
