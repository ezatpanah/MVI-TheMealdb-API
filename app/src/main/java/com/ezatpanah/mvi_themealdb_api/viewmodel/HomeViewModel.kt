package com.ezatpanah.mvi_themealdb_api.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezatpanah.mvi_themealdb_api.intent.home.HomeIntent
import com.ezatpanah.mvi_themealdb_api.repository.MainRepository
import com.ezatpanah.mvi_themealdb_api.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    val intentChannel = Channel<HomeIntent>()
    private val _state = MutableStateFlow<HomeState>(HomeState.Idle)
    val state: StateFlow<HomeState> get() = _state

    init {
        handleIntents()
    }

    private fun handleIntents() = viewModelScope.launch {
        intentChannel.consumeAsFlow().collect { intent ->
            when (intent) {
                is HomeIntent.GetFilterLetters -> fetchingFiltersList()
                is HomeIntent.GetRandom -> fetchingRandomFood()
                is HomeIntent.GetCategoriesList -> fetchingCategoriesList()
                is HomeIntent.GetFoodList -> fetchingFoodsList(intent.letter)
                is HomeIntent.GetSearchFood -> fetchingSearchFood(intent.search)
                is HomeIntent.GetFoodByCategory -> fetchingFoodsByCategory(intent.cat)
            }
        }
    }

    private fun fetchingFoodsByCategory(cat: String) {

    }

    private fun fetchingSearchFood(search: String) {

    }

    private fun fetchingFoodsList(letter: String) {

    }

    private fun fetchingCategoriesList() {

    }

    private suspend fun fetchingFiltersList() {
        val list = listOf('A'..'Z').flatten().toMutableList()
        _state.emit(HomeState.FilterLetters(list))
    }


    private suspend fun fetchingRandomFood() {
        val response = repository.randomFood()
        when (response.code()) {
            in 200..202 -> {
                _state.emit(HomeState.RandomFood(response.body()?.meals?.get(0)))
            }
            in 400..499 -> {
                _state.emit(HomeState.Error(""))
            }
            in 500..599 -> {
                _state.emit(HomeState.Error(""))
            }
        }
    }


}