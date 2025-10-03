package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.JokeItem
import kotlinx.coroutines.flow.Flow


interface ChuckNorrisUseCase {
    fun searchChuckNorrisJokes(param: String): Flow<Resource<List<JokeItem>>>
}