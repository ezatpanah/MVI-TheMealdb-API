package com.ezatpanah.mvi_themealdb_api.intent.home

sealed class HomeIntent{
    object GetFilterLetters : HomeIntent()
    object GetRandom : HomeIntent()
    object GetCategoriesList : HomeIntent()
    data class  GetFoodList(val letter : String) : HomeIntent()
    data class GetSearchFood(val search : String) : HomeIntent()
    data class GetFoodByCategory(val cat : String) : HomeIntent()
}
