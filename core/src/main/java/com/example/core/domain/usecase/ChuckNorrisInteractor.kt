package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.response.JokeItem
import com.example.core.domain.repository.IChuckNorrisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ChuckNorrisInteractor @Inject constructor(private val repo: IChuckNorrisRepository): ChuckNorrisUseCase {
    override fun searchChuckNorrisJokes(param: String): Flow<Resource<List<JokeItem>>> =
        repo.searchChuckNorrisJokes(param)
}