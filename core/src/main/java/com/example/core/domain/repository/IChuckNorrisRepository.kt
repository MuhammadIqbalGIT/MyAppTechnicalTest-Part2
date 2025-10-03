package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.JokeItem
import kotlinx.coroutines.flow.Flow

interface IChuckNorrisRepository {
    fun searchChuckNorrisJokes(param: String): Flow<Resource<List<JokeItem>>>
}

