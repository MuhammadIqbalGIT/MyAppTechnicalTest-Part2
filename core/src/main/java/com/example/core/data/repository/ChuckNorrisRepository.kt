package com.example.core.data.repository

import com.example.core.data.source.OnlyNetworkBoundResource
import com.example.core.data.source.Resource
import com.example.core.data.source.remote.datasource.ChuckNorrisDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.JokeItem
import com.example.core.domain.repository.IChuckNorrisRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ChuckNorrisRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataSource: ChuckNorrisDataSource
) : IChuckNorrisRepository {

    override fun searchChuckNorrisJokes(param: String): Flow<Resource<List<JokeItem>>> {
        return object : OnlyNetworkBoundResource<List<JokeItem>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<JokeItem>>> {
                return dataSource.searchChuckNorrisJokes(param)
            }
        }.asFlow()
    }
}