package com.ezatpanah.mvi_themealdb_api.repository

import com.ezatpanah.mvi_themealdb_api.api.ApiServices
import com.ezatpanah.mvi_themealdb_api.db.FoodDao
import com.ezatpanah.mvi_themealdb_api.db.FoodEntity
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServices: ApiServices, private val dao: FoodDao) {
    suspend fun randomFood() = apiServices.getFoodRandom()
    suspend fun categoriesList() = apiServices.getCategoriesList()
    suspend fun foodsList(letter: String) = apiServices.getFoodList(letter)
    suspend fun searchFood(letter: String) = apiServices.searchList(letter)
    suspend fun foodByCategory(letter: String) = apiServices.filterList(letter)
    suspend fun detailFood(id: Int) = apiServices.getFoodDetails(id)
    suspend fun saveFood(entity: FoodEntity) = dao.saveFood(entity)
    suspend fun deleteFood(entity: FoodEntity) = dao.deleteFood(entity)
    fun existsFood(id: Int) = dao.existsFood(id)
    fun foodsList() = dao.getAllFoods()


}