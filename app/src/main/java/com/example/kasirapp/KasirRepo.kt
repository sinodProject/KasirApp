package com.example.kasirapp

import androidx.lifecycle.LiveData

class KasirRepo (private val daoKsr: DaoKsr){
    val foods : LiveData<List<Food>> = daoKsr.getFood()
    val beverages : LiveData<List<Beverage>> = daoKsr.getBeverage()
    val user : LiveData<List<User>> = daoKsr.getUser()
    val table : LiveData<List<Meja>> = daoKsr.getTable()


    suspend fun insertFood(food: Food){
        daoKsr.insertFood(food)
    }

    suspend fun deleteFood(food: Food){
        daoKsr.deleteFood(food)
    }

    suspend fun updateFood(food: Food){
        daoKsr.updateFood(food)
    }

    suspend fun insertBeverage(beverage: Beverage){
        daoKsr.insertBeverage(beverage)
    }

    suspend fun deleteBeverage(beverage: Beverage){
        daoKsr.deleteBeverage(beverage)
    }

    suspend fun updateBeverage(beverage: Beverage){
        daoKsr.updateBeverage(beverage)
    }
    suspend fun insertUser(user: User){
        daoKsr.insertUser(user)
    }

    suspend fun deleteUser(user: User){
        daoKsr.deleteUser(user)
    }

    suspend fun updateUser(user: User){
        daoKsr.updateUser(user)
    }

    suspend fun insertTable(meja: Meja){
        daoKsr.insertMeja(meja)
    }

    suspend fun deleteTable(meja: Meja){
        daoKsr.deleteMeja(meja)
    }

    suspend fun updateTable(meja: Meja){
        daoKsr.updateMeja(meja)
    }



}