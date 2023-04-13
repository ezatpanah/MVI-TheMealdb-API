package com.ezatpanah.mvi_themealdb_api.state

import com.ezatpanah.mvi_themealdb_api.response.CategoriesListResponse
import com.ezatpanah.mvi_themealdb_api.response.FoodsListResponse

sealed class HomeState {
    object Idle : HomeState()
    object LoadingCategory : HomeState()
    object LoadingFoods : HomeState()

    data class FilterLetters(val letters: MutableList<Char>) : HomeState()
    data class RandomFood(val food: FoodsListResponse.Meal?) : HomeState()
    data class CtegoriesList(val cat: MutableList<CategoriesListResponse.Category>) : HomeState()
    data class FoodList (val foods : MutableList<FoodsListResponse.Meal>): HomeState()
    data class Error(val error: String) : HomeState()


}
