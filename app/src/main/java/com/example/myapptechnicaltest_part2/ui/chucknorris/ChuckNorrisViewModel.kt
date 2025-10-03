package com.example.myapptechnicaltest_part2.ui.chucknorris

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.JokeItem
import com.example.core.domain.usecase.ChuckNorrisUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChuckNorrisViewModel @Inject constructor(
    private val useCase: ChuckNorrisUseCase

) : ViewModel() {
    private val _searchChuckNorris = MutableStateFlow<Resource<List<JokeItem>>>(Resource.Standby())
    val searchChuckNorris: Flow<Resource<List<JokeItem>>>
        get() = _searchChuckNorris


    fun getMealsDetailsById(param : String){
        viewModelScope.launch {
            _searchChuckNorris.value = Resource.Loading()
            useCase.searchChuckNorrisJokes(param).collect{
                _searchChuckNorris.value = it
            }
        }
    }
}